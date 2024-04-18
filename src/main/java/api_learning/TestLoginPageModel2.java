package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import model.components.login.LoginFormCompModel2;
import model.pages.LoginPageModel2;

public class TestLoginPageModel2 {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);
        try{
            LoginPageModel2 loginPage = new LoginPageModel2(driver);
            loginPage.getBottomNavigateComp().clickOnLoginIcon();

            LoginFormCompModel2 loginFormComp = loginPage.getLoginFormComp();
            loginFormComp.inputEmail("teo@gmail.com");
            loginFormComp.inputPassword("12345678");
            loginFormComp.clickLogin();
        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
