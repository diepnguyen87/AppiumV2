package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.util.Arrays;
import java.util.List;

import static java.time.Duration.ofMillis;

public class NarrowDown {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);

        try {
            Dimension dimension = driver.manage().window().getSize();
            int deviceHeight = dimension.getHeight();
            int deviceWidth = dimension.getWidth();

            int startX = 50 * (deviceWidth / 100);
            int endX = 50 * (deviceWidth / 100);
            int startY = 0 * (deviceHeight / 100);
            int endY = 50 * (deviceHeight / 100);

            PointerInput swipeAction = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence swipeDown = new Sequence(swipeAction, 1);
            swipeDown.addAction(swipeAction.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
            swipeDown.addAction(swipeAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipeDown.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
            swipeDown.addAction(swipeAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipeDown));

            String androidSystemLocator = "//android.widget.TextView[@resource-id=\"android:id/app_name_text\" and @text=\"Android System\"]/parent::android.view.ViewGroup/following-sibling::android.widget.Button";
            driver.findElement(AppiumBy.xpath(androidSystemLocator)).click();

            String notiLocatorList = "//android.widget.FrameLayout[@resource-id=\"com.android.systemui:id/expandableNotificationRow\"]//android.view.ViewGroup[@resource-id=\"com.android.systemui:id/notification_children_container\"]//android.widget.FrameLayout[@resource-id=\"com.android.systemui:id/expandableNotificationRow\"]";
            List<WebElement> notiList = driver.findElements(By.xpath(notiLocatorList));
            if(notiList.size() > 0){
                for (WebElement noti : notiList) {
                    WebElement notiHeader = noti.findElement(AppiumBy.id("android:id/title"));
                    System.out.println(notiHeader.getText());
                    WebElement notiBody = noti.findElement(AppiumBy.id("android:id/text"));
                    System.out.println(notiBody.getText());
                }
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
