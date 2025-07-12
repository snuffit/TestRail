package tests.ui.login;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.ui.BaseTest;
import utils.PropertyReader;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Epic("Login with valid value")
    @Feature("Login")
    @Story("Login with valid value")
    @Test(testName = "Login with valid value")
    public void loginWithValidValue() {
        loginStep.auth(email, password);
    }

    @DataProvider(name = "Invalid login value")
    public Object[][] invalidLoginValue() {
        String email = System.getProperty("email", PropertyReader.getProperty("email"));
        String password = System.getProperty("password", PropertyReader.getProperty("password"));
        return new Object[][] {
                {"Incorrect", password, "Email/Login or Password is incorrect. Please try again."},
                {email, "Incorrect", "Email/Login or Password is incorrect. Please try again."}
        };
    }

    @Epic("Login with valid value")
    @Feature("Login")
    @Story("Login with valid value")
    @Test(testName = "Login with valid value", dataProvider = "Invalid login value")
    public void loginWithInvalidValue(String email, String password, String errorMessage) {
        loginStep.auth(email, password);
        assertThat(loginPage.getErrorMessage())
                .isEqualTo(errorMessage);
    }
}
