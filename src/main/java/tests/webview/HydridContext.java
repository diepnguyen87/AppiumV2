package tests.webview;

import api_learning.Context;
import api_learning.WaitMoreThanOneContext;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HydridContext extends BaseTest {

    @Test
    public void TestWebview() throws InterruptedException {
        AppiumDriver driver = getDriver();
        driver.findElement(AppiumBy.accessibilityId("Webview")).click();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        explicitWait.until(new WaitMoreThanOneContext(driver));

        SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
        String webViewContext = contextSwitchingDriver.getContextHandles()
                .stream()
                .filter(context -> context.startsWith("WEBVIEW"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No WebView context found"));
        contextSwitchingDriver.context(webViewContext);

        driver.findElement(By.cssSelector(".navbar__toggle")).click();
        List<WebElement> menuItemList = driver.findElements(By.cssSelector("li.menu__list-item a.menu__link"));
        if (menuItemList.isEmpty()) {
            throw new RuntimeException("[ERROR] Menu list item is empty");
        }

        List<MenuItem> actualMenuItemList = new ArrayList<>();
        for (WebElement menuItem : menuItemList) {
            if (menuItem.getText().isEmpty()) {
                actualMenuItemList.add(new MenuItem("Icon", menuItem.getAttribute("href")));
            } else {
                actualMenuItemList.add(new MenuItem(menuItem.getText(), menuItem.getAttribute("href")));
            }
        }
        System.out.println(actualMenuItemList);
        contextSwitchingDriver.context(Context.NATIVE_VIEW);
        driver.findElement(AppiumBy.accessibilityId("Home")).click();
    }

    public static class MenuItem {
        private String name;
        private String href;

        public MenuItem(String name, String href) {
            this.name = name;
            this.href = href;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
