package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
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

public class SwipeHorizontal {
    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);

        try {
            driver.findElement(AppiumBy.accessibilityId(("Swipe"))).click();

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            Dimension dimension = driver.manage().window().getSize();
            int deviceHeight = dimension.getHeight();
            int deviceWidth = dimension.getWidth();

            int startX = 80 * (deviceWidth / 100);
            int endX = 20 * (deviceWidth / 100);
            int startY = 70 * (deviceHeight / 100);
            int endY = 70 * (deviceHeight / 100);

            for (int i = 0; i < 5; i++) {
                List<WebElement> elementList = driver.findElements(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]//android.widget.TextView[@text=\"JS.FOUNDATION\"]"));
                if (elementList.size() == 1) {
                    System.out.println(elementList.get(0).getText());
                    break;
                } else {
                    PointerInput swipeAction = new PointerInput(Kind.TOUCH, "finger1");
                    Sequence swipeDown = new Sequence(swipeAction, 1);
                    swipeDown.addAction(swipeAction.createPointerMove(ofMillis(0), Origin.viewport(), startX, startY));
                    swipeDown.addAction(swipeAction.createPointerDown(MouseButton.LEFT.asArg()));
                    swipeDown.addAction(swipeAction.createPointerMove(ofMillis(1000), Origin.viewport(), endX, endY));
                    swipeDown.addAction(swipeAction.createPointerUp(MouseButton.LEFT.asArg()));
                    driver.perform(Arrays.asList(swipeDown));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
