package api_learning;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import model.components.login.LoginFormCompModel3;
import model.pages.LoginPageModel3;

public class TestLoginPageModel3 {

    public static void main(String[] args) {
        AppiumDriver driver = new DriverFactory().getDriver(Platform.ANDROID);
        try {
            LoginPageModel3 loginPage = new LoginPageModel3(driver);
            loginPage.getBottomNavigateComp().clickOnLoginIcon();

            LoginFormCompModel3 loginFormComp = loginPage.getLoginFormComp();
            loginFormComp
                    .inputEmail("teo@gmail.com")
                    .inputPassword("12345678")
                    .clickOnLoginBtn();
            ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.SPACE));
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
