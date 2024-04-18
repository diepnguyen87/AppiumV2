package source;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumLauching {

    public static void main(String[] args) throws InterruptedException {
        AppiumDriver driver = null;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("appPackage", "com.wdiodemoapp");
        caps.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(appiumServer == null){
            throw new RuntimeException("[ERROR] incorrect Appium Server URL");
        }

        driver = new AndroidDriver(appiumServer, caps);
        Thread.sleep(3000);
    }
}
