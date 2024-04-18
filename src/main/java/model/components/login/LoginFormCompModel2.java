package model.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginFormCompModel2 {

    private AppiumDriver driver;
    private By emailSel = AppiumBy.accessibilityId("input-email");
    private By passwordSel = AppiumBy.accessibilityId("input-password");
    private By loginSel = AppiumBy.accessibilityId("button-LOGIN");

    public LoginFormCompModel2(AppiumDriver driver){
        this.driver = driver;
    }

    public void inputEmail(String email){
        driver.findElement(emailSel).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordSel).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginSel).click();
    }
}
