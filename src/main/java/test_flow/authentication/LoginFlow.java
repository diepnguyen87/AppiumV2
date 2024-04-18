package test_flow.authentication;

import io.appium.java_client.AppiumDriver;
import model.components.login.LoginFormCompModel3;
import model.pages.LoginPageModel3;
import org.apache.commons.validator.routines.EmailValidator;
import test_flow.BaseFlow;

public class LoginFlow extends BaseFlow {
    private String email;
    private String password;

    public LoginFlow(AppiumDriver driver, String email, String password) {
        super(driver);
        this.email = email;
        this.password = password;
    }

    public void login() {
        LoginFormCompModel3 loginFormComp = new LoginPageModel3(driver).getLoginFormComp();
        loginFormComp.inputEmail(email);
        loginFormComp.inputPassword(password);
        loginFormComp.clickOnLoginBtn();
    }

    public void verifyLogin() {
        LoginFormCompModel3 loginFormComp = new LoginPageModel3(driver).getLoginFormComp();
        boolean isEmailValid = validateEmail();
        boolean isPasswordValid = validatePassword();

        if (isEmailValid && isPasswordValid) {
            verifyCorrectCredential(loginFormComp);
            return;
        }

        if (!isEmailValid) {
            verifyIncorrectEmail(loginFormComp);
        }

        if (!isPasswordValid) {
            verifyIncorrectPassword(loginFormComp);
        }
    }

    private boolean validateEmail() {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean validatePassword() {
        return password.length() >= 8;
    }

    private void verifyCorrectCredential(LoginFormCompModel3 loginFormComp) {
        System.out.println("Alert Title: " + loginFormComp.getSuccessTitle());
        System.out.println("Alert Message: " + loginFormComp.getSuccessMessage());
    }

    private void verifyIncorrectEmail(LoginFormCompModel3 loginFormComp) {
        System.out.println("Actual invalid email message: " + loginFormComp.getInvalidEmailMessage());
        System.out.println("Expected invalid email message: " + "Please enter a valid email address");
    }

    private void verifyIncorrectPassword(LoginFormCompModel3 loginFormComp) {
        System.out.println("Actual invalid email message: " + loginFormComp.getInvalidPasswordMessage());
        System.out.println("Expected invalid email message: " + "Please enter at least 8 characters");
    }
}
