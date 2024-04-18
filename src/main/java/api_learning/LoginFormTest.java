package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormTest extends DriverFactory {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);

        try{
            //Open login form
            driver.findElement(AppiumBy.accessibilityId("Login1")).click();

            //Interact with elements on login form
            driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("teo@gmail.com");
            driver.findElement(AppiumBy.accessibilityId("input-password")).sendKeys("12345678");
            driver.findElement(AppiumBy.accessibilityId("button-LOGIN")).click();

            //verify success popup
            By successPopupTitleElem = AppiumBy.id("android:id/alertTitle");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10L));
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(successPopupTitleElem));
            System.out.println(driver.findElement(successPopupTitleElem));
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
