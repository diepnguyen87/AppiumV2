package tests.authentication;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.LoginCredential;
import test_data.util.DataObjectBuilder;
import test_flow.authentication.LoginFlow;
import tests.BaseTest;


public class TestLogin extends BaseTest {
    @Test(dataProvider = "dataList")
    public void testLogin(LoginCredential credential) {
        AppiumDriver driver = getDriver();
        LoginFlow loginFlow = new LoginFlow(driver, credential.getEmail(), credential.getPassword());
        loginFlow.navigateToLoginPage();
        loginFlow.login();
        loginFlow.verifyLogin();
    }

    @DataProvider(name = "dataList")
    public LoginCredential[] getCredentialDataList() {
        String jsonFilePath = "/src/main/java/test_data/model/LoginCredentials.json";
        return DataObjectBuilder.buildDataObject(jsonFilePath, LoginCredential[].class);
    }
}
