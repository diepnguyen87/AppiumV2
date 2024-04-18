package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import model.components.login.LoginFormCompModel1;
import model.pages.LoginPageModel1;

public class TestLoginPageModel1 {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);
        try{
            LoginPageModel1 loginPage = new LoginPageModel1(driver);
            loginPage.getBottomNavigateComp().loginElem().click();

            LoginFormCompModel1 loginFormComp = loginPage.getLoginFormComp();
            loginFormComp.emailElem().sendKeys("teo@gmail.com");
            loginFormComp.passwordElem().sendKeys("12345678");
            loginFormComp.loginBtnElem();
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
