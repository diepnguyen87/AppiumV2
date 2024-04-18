package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitMoreThanOneContext implements ExpectedCondition<Boolean> {

    private AppiumDriver driver;

    public WaitMoreThanOneContext(AppiumDriver driver){
        this.driver = driver;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
        return contextSwitchingDriver.getContextHandles().size() > 1;
    }
}
