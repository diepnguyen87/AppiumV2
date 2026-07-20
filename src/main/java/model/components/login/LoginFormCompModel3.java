package model.components.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginFormCompModel3 {

    private AppiumDriver driver;
    private By emailSel = AppiumBy.accessibilityId("input-email");

    private By androidInvalidEmailMsgSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter a valid email address\"]");
    private By iosInvalidEmailMsgSel = AppiumBy.iOSNsPredicateString("value='Please enter a valid email address']");

    private By passwordSel = AppiumBy.accessibilityId("input-password");

    private By androidInvalidPasswordMsgSel = AppiumBy.xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");
    private By iosInvalidPasswordMsgSel = AppiumBy.iOSNsPredicateString("value='Please enter at least 8 characters'");

    private By loginSel = AppiumBy.accessibilityId("button-LOGIN");

    private By androidAlertTitleSel = AppiumBy.id("com.wdiodemoapp:id/alert_title");
    private By iosAlertTitleSel = AppiumBy.iOSNsPredicateString("value='Success'");

    private By androidAlertMsgSel = AppiumBy.id("android:id/message");
    private By iosAlertMsgSel = AppiumBy.iOSNsPredicateString("value='You are logged in!'");

    String platformName;
    public LoginFormCompModel3(AppiumDriver driver) {
        this.driver = driver;
        platformName = driver.getCapabilities().getPlatformName().name();
    }

    @Step("Input email {email}")
    public LoginFormCompModel3 inputEmail(String email) {
        driver.findElement(emailSel).sendKeys(email);
        return this;
    }

    public String getInvalidEmailMessage(){
        if(platformName.equalsIgnoreCase("android")){
            return driver.findElement(androidInvalidEmailMsgSel).getText();
        }else{
            return driver.findElement(iosInvalidEmailMsgSel).getText();
        }
    }

    @Step("Input password {password}")
    public LoginFormCompModel3 inputPassword(String password) {
        driver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public String getInvalidPasswordMessage(){
        if(platformName.equalsIgnoreCase("android")){
            return driver.findElement(androidInvalidPasswordMsgSel).getText();
        }else {
            return driver.findElement(iosInvalidPasswordMsgSel).getText();
        }
    }

    public LoginFormCompModel3 clickOnLoginBtn() {
        driver.findElement(loginSel).click();
        return this;
    }

    public String getSuccessTitle(){
        if(platformName.equalsIgnoreCase("android")){
            return driver.findElement(androidAlertTitleSel).getText();
        }else {
            return driver.findElement(iosAlertTitleSel).getText();
        }
    }

    public String getSuccessMessage(){
        if(platformName.equalsIgnoreCase("android")){
            return driver.findElement(androidAlertMsgSel).getText();
        }else {
            return driver.findElement(iosAlertMsgSel).getText();
        }
    }
}
