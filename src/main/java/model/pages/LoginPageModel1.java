package model.pages;

import io.appium.java_client.AppiumDriver;
import model.components.global.BottomNavigateCompModel1;
import model.components.login.LoginFormCompModel1;

public class LoginPageModel1 {

    private AppiumDriver driver;
    public LoginPageModel1(AppiumDriver driver){
        this.driver = driver;
    }

    public BottomNavigateCompModel1 getBottomNavigateComp(){
        return new BottomNavigateCompModel1(driver);
    }

    public LoginFormCompModel1 getLoginFormComp(){
        return new LoginFormCompModel1(driver);
    }
}
