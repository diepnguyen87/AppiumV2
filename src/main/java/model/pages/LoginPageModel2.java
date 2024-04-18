package model.pages;

import io.appium.java_client.AppiumDriver;
import model.components.global.BottomNavigateCompModel2;
import model.components.login.LoginFormCompModel2;

public class LoginPageModel2 {

    private AppiumDriver driver;
    public LoginPageModel2(AppiumDriver driver){
        this.driver = driver;
    }

    public BottomNavigateCompModel2 getBottomNavigateComp(){
        return new BottomNavigateCompModel2(driver);
    }

    public LoginFormCompModel2 getLoginFormComp(){
        return new LoginFormCompModel2(driver);
    }
}
