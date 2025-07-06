package tests.ui;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest{

    @Test
    public void loginWithValidValue() {
        //auth(user, password)
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .clickLogInButton()
                .isPageOpened();
    }

    @DataProvider
    public Object[][] invalidLoginValue() {
        String email = Dotenv.load().get("EMAIL");
        String password = Dotenv.load().get("PASSWORD");
        return new Object[][] {
                {"Incorrect", password, "Email/Login or Password is incorrect. Please try again."},
                {email, "Incorrect", "Email/Login or Password is incorrect. Please try again."}
        };
    }

    @Test(dataProvider = "invalidLoginValue")
    public void loginWithInvalidValue(String email, String password, String errorMessage) {
        //auth(user, password)
        loginPage.openPage()
                .isPageOpened()
                .login(email, password)
                .clickLogInButton()
                .isPageOpened();
        assertThat(loginPage.getErrorMessage())
                .isEqualTo(errorMessage);
    }
}
