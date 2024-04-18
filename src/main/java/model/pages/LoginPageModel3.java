package model.pages;

import io.appium.java_client.AppiumDriver;
import model.components.global.BottomNavigateCompModel2;
import model.components.login.LoginFormCompModel3;

public class LoginPageModel3 {

    private AppiumDriver driver;
    public LoginPageModel3(AppiumDriver driver){
        this.driver = driver;
    }

    public BottomNavigateCompModel2 getBottomNavigateComp(){
        return new BottomNavigateCompModel2(driver);
    }

    public LoginFormCompModel3 getLoginFormComp(){
        return new LoginFormCompModel3(driver);
    }
}
