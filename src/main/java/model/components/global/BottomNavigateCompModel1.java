package model.components.global;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BottomNavigateCompModel1 {

    private AppiumDriver driver;
    private By loginSel = AppiumBy.accessibilityId("Login");

    public BottomNavigateCompModel1(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement loginElem(){
        return driver.findElement(loginSel);
    }
}
