package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class XpathLearning {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);

        try {
            driver.findElement(AppiumBy.accessibilityId(("Login"))).click();

            List<WebElement> elementList = driver.findElements(AppiumBy.xpath("//android.widget.EditText"));
            final int EMAIL_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            elementList.get(EMAIL_INDEX).sendKeys("teo@gmail.com");
            elementList.get(PASSWORD_INDEX).sendKeys("12345678");

            System.out.println(driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"When the device\")")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
