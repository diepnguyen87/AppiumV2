package api_learning;

import com.google.common.io.Files;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;

public class CaptureScreenshot {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);
        try{
            driver.findElement(AppiumBy.accessibilityId("Login")).click();
            File sourceFile = driver.getScreenshotAs(OutputType.FILE);

            //capture all screen
            String targetPath = System.getProperty("user.dir").concat("\\Screenshot").concat("\\LoginForm.png");
            Files.copy(sourceFile, new File(targetPath));

            //capture a partial screen
            WebElement loginScreenElem = driver.findElement(AppiumBy.accessibilityId("Login-screen"));
            sourceFile = loginScreenElem.getScreenshotAs(OutputType.FILE);
            String partialTargetpath = System.getProperty("user.dir").concat("\\Screenshot").concat("\\PartialLoginForm.png");
            Files.copy(sourceFile, new File(partialTargetpath));

            //capture login button
            WebElement loginButtonElem = driver.findElement(AppiumBy.accessibilityId("button-LOGIN"));
            sourceFile = loginButtonElem.getScreenshotAs(OutputType.FILE);
            String elementTargetPath = System.getProperty("user.dir").concat("\\Screenshot").concat("\\LoginButton.png");
            Files.copy(sourceFile, new File(elementTargetPath));


        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
