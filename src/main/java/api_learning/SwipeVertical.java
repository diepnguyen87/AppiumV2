package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.time.Duration.ofMillis;


public class SwipeVertical {
    private static AppiumDriver driver;

    public static void main(String[] args) {
        driver = new DriverFactory().getDriver(Platform.ANDROID);

        try {
            driver.findElement(AppiumBy.accessibilityId(("Forms"))).click();

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Form Components\")")));

            WebElement textInputElem = driver.findElement(AppiumBy.accessibilityId("text-input"));
            System.out.println(textInputElem.getAttribute("hint"));

            WebElement switchElem = driver.findElement(AppiumBy.accessibilityId("switch"));
            WebElement switchTextElem = driver.findElement(AppiumBy.accessibilityId("switch-text"));
            switchElem.click();
            //Click to turn the switch OFF
            System.out.println(switchElem.getText());
            switchElem.click();
            //Click to turn the switch ON
            System.out.println(switchElem.getText());

            WebElement dropdownSel = driver.findElement(AppiumBy.accessibilityId("Dropdown"));
            dropdownSel.click();

            List<WebElement> dropdownOptions = driver.findElements(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"com.wdiodemoapp:id/select_dialog_listview\"]/android.widget.CheckedTextView"));
            for (WebElement option : dropdownOptions) {
                System.out.println(option.getText());
            }
            driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"com.wdiodemoapp:id/select_dialog_listview\"]")).click();

            swipeVerticalDown(50, 50, 50, 20);
            driver.findElement(AppiumBy.accessibilityId("button-Active")).click();

            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/alertTitle")));
            System.out.println(driver.findElement(AppiumBy.id("android:id/message")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    private static void swipeVerticalDown(int startXRatio, int startYRatio, int endXRatio, int endYRatio) {
        Dimension dimension = driver.manage().window().getSize();
        int deviceHeight = dimension.getHeight();
        int deviceWidth = dimension.getWidth();

        int startX = startXRatio * (deviceWidth / 100);
        int endX = endXRatio * (deviceWidth / 100);
        int startY = startYRatio * (deviceHeight / 100);
        int endY = endYRatio * (deviceHeight / 100);

        PointerInput swipeAction = new PointerInput(Kind.TOUCH, "finger1");
        Sequence swipeDown = new Sequence(swipeAction, 1);
        swipeDown.addAction(swipeAction.createPointerMove(ofMillis(0), Origin.viewport(), startX, startY));
        swipeDown.addAction(swipeAction.createPointerDown(MouseButton.LEFT.asArg()));
        swipeDown.addAction(new Pause(swipeAction, Duration.ofMillis(200)));
        swipeDown.addAction(swipeAction.createPointerMove(ofMillis(1000), Origin.viewport(), endX, endY));
        swipeDown.addAction(swipeAction.createPointerUp(MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipeDown));
    }
}
