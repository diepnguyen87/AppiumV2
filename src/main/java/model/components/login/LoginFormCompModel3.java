package model.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginFormCompModel3 {

    private AppiumDriver driver;
    private By emailSel = AppiumBy.accessibilityId("input-email");

    private By invalidEmailMsgSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter a valid email address\"]");
    private By passwordSel = AppiumBy.accessibilityId("input-password");

    private By invalidPasswordMsgSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");
    private By loginSel = AppiumBy.accessibilityId("button-LOGIN");

    private By alertTitleSel = AppiumBy.id("com.wdiodemoapp:id/alert_title");

    private By alertMsgSel = AppiumBy.id("android:id/message");

    public LoginFormCompModel3(AppiumDriver driver) {
        this.driver = driver;
    }

    @Step("Input email {email}")
    public LoginFormCompModel3 inputEmail(String email) {
        driver.findElement(emailSel).sendKeys(email);
        return this;
    }

    public String getInvalidEmailMessage(){
        return driver.findElement(invalidEmailMsgSel).getText();
    }

    @Step("Input password {password}")
    public LoginFormCompModel3 inputPassword(String password) {
        driver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public String getInvalidPasswordMessage(){
        return driver.findElement(invalidPasswordMsgSel).getText();
    }

    public LoginFormCompModel3 clickOnLoginBtn() {
        driver.findElement(loginSel).click();
        return this;
    }

    public String getSuccessTitle(){
        return driver.findElement(alertTitleSel).getText();
    }

    public String getSuccessMessage(){
        return driver.findElement(alertMsgSel).getText();
    }
}
