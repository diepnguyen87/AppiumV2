package model.components.global;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class BottomNavigateCompModel2 {

    private AppiumDriver driver;
    private By loginIconSel = AppiumBy.accessibilityId("Login");

    public BottomNavigateCompModel2(AppiumDriver driver){
        this.driver = driver;
    }

    public void clickOnLoginIcon(){
        driver.findElement(loginIconSel).click();
    }
}
