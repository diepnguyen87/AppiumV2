package test_flow;

import io.appium.java_client.AppiumDriver;
import model.components.global.BottomNavigateCompModel2;

public class BaseFlow {
    protected AppiumDriver driver;

    public BaseFlow(AppiumDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage(){
        new BottomNavigateCompModel2(driver).clickOnLoginIcon();
    }
}
