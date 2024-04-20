package driver;

import api_learning.AppPackages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class DriverFactory implements MobileCapabilityType, AppPackages {

    private AppiumDriver driver;

    public AppiumDriver getDriver(Platform platformName, String platformVersion, String udid, String systemPort) {

        DesiredCapabilities caps = new DesiredCapabilities();
        if (driver == null) {
            String remoteEnvironmentVariable = System.getenv("isRemote");
            String remotePropertyVariable = System.getProperty("isRemote");
            String isRemote = remoteEnvironmentVariable == null ? remotePropertyVariable : remoteEnvironmentVariable;

            if (isRemote == null) {
                throw new IllegalArgumentException("[ERROR] Please supply an environment or property variable [isRemote] in commandline/IDE");
            }

            String targetURL = "http://localhost:4723";
            if (isRemote.equalsIgnoreCase("true")) {
                String urlEnvironmentVariable = System.getenv("url");
                String urlPropertyVariable = System.getProperty("url");

                targetURL = urlEnvironmentVariable == null ? urlPropertyVariable : urlEnvironmentVariable;
                if (targetURL == null) {
                    throw new IllegalArgumentException("[ERROR] Please input an environment or property variable [url] in commandline/IDE");
                }
            }
            URL appiumServer = null;
            try {
                appiumServer = new URL(targetURL);
            } catch (Exception e) {
                throw new RuntimeException("[ERROR] Somehow, we couldn't construct Appium server URL");
            }

            caps.setCapability(PLATFORM_NAME, platformName);
            if (!platformVersion.equals("platformVersion")) {
                caps.setCapability(PLATFORM_VERION, platformVersion);
            }

            switch (platformName) {
                case ANDROID -> {
                    caps.setCapability(UDID, udid);
                    caps.setCapability(AUTOMATION_NAME, "uiautomator2");
                    caps.setCapability(APP_PACKAGE, AppPackages.WDIO);
                    caps.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                    caps.setCapability(SYSTEM_PORT, systemPort);
                    driver = new AndroidDriver(appiumServer, caps);
                }
                case IOS -> {
                    caps.setCapability(AUTOMATION_NAME, "xcuitest");
                    caps.setCapability(DEVICE_NAME, udid);
                    caps.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
                    caps.setCapability(WDA_LOCAL_PORT, systemPort);
                    driver = new IOSDriver(appiumServer, caps);
                }
                default -> {
                    throw new IllegalArgumentException("[ERROR] incorrect platformName name");
                }
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        }
        return driver;
    }

    public AppiumDriver getDriver(Platform platform) {
        DesiredCapabilities caps = new DesiredCapabilities();

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/");
        } catch (Exception e) {
            throw new RuntimeException("[ERROR] Somehow, we couldn't construct Appium server URL");
        }

        switch (platform) {
            case ANDROID -> {
                caps.setCapability(PLATFORM_NAME, "android");
                caps.setCapability(UDID, "emulator-5554");
                caps.setCapability(AUTOMATION_NAME, "uiautomator2");
                caps.setCapability(APP_PACKAGE, AppPackages.WDIO);
                caps.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                driver = new AndroidDriver(appiumServer, caps);
            }
            case IOS -> {
                caps.setCapability(PLATFORM_NAME, "ios");
                caps.setCapability(PLATFORM_VERION, "");
                caps.setCapability(DEVICE_NAME, "");
                caps.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
                caps.setCapability(AUTOMATION_NAME, "xcuitest");
                caps.setCapability(APP, "");
                driver = new IOSDriver(appiumServer, caps);
            }
            default -> {
                throw new IllegalArgumentException("[ERROR] incorrect platform name");
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        return driver;
    }

    public void resetApp(String platformName) {
        InteractsWithApps interactDriver = ((InteractsWithApps) driver);
        Capabilities caps = driver.getCapabilities();
        String appName = Platform.valueOf(platformName) == Platform.ANDROID ? caps.getCapability(APP_PACKAGE).toString() : caps.getCapability(BUNDLE_ID).toString();
        interactDriver.terminateApp(appName);
        interactDriver.activateApp(appName);
    }

    public void quitAppiumSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}
