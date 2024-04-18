package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HandleMultipleApps {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);
        InteractsWithApps interactsWithApps = ((InteractsWithApps) driver);

        try {
            //Open login form
            driver.findElement(AppiumBy.accessibilityId("Login")).click();

            //Interact with elements on login form
            driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("teo@gmail.com");
            driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("12345678");
            driver.findElement(AppiumBy.accessibilityId("button-LOGIN")).click();

            /*((InteractsWithApps) driver).runAppInBackground(Duration.ofSeconds(3));*/
            interactsWithApps.activateApp(AppPackages.SETTING);
            driver.findElement(AppiumBy.xpath("//*[@text='Network & internet']")).click();
            driver.findElement(AppiumBy.xpath("//*[@text='Internet']")).click();

            boolean isWifiOn = driver.findElements(By.xpath("//*[@text='Add network']")).isEmpty();
            int timeToToggle = isWifiOn ? 2 : 1;

            WebElement wifiSwitch = driver.findElement(AppiumBy.className("android.widget.Switch"));
            for (int i = 0; i < timeToToggle; i++) {
                wifiSwitch.click();
            }

            interactsWithApps.activateApp(AppPackages.WDIO);
            driver.findElement(AppiumBy.xpath("//*[@text='OK']")).click();
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //driver.executeScript("mobile: terminateApp", ImmutableMap.of("appId", AppPackages.SETTING));
        driver.quit();
    }
}
