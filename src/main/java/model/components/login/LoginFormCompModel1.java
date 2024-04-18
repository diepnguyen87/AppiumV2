package model.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginFormCompModel1 {

    private AppiumDriver driver;
    private By emailSel = AppiumBy.accessibilityId("input-email");
    private By passwordSel = AppiumBy.accessibilityId("input-password");
    private By loginSel = AppiumBy.accessibilityId("button-LOGIN");

    public LoginFormCompModel1(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement emailElem(){
        return driver.findElement(emailSel);
    }

    public WebElement passwordElem(){
        return driver.findElement(passwordSel);
    }

    public WebElement loginBtnElem(){
        return driver.findElement(loginSel);
    }
}
