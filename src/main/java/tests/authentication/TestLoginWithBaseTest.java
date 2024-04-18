package tests.authentication;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.LoginCredential;
import test_data.util.DataObjectBuilder;
import test_flow.authentication.LoginFlow;
import tests.BaseTest;

import static io.qameta.allure.SeverityLevel.CRITICAL;


public class TestLoginWithBaseTest extends BaseTest {
    @Test(dataProvider = "dataList")
    @Severity(CRITICAL)
    @Owner("Diep Nguyen")
    @Link(name = "LinkText", url = "https://allurereport.org/docs/testng/")
    @Issue("Issue1")
    @TmsLink("TMS-456")
    public void testLogin(LoginCredential credential) {
        LoginFlow loginFlow = new LoginFlow(getDriver(), credential.getEmail(), credential.getPassword());
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
