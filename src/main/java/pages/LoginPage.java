package pages;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private final String TITLE_TEXT = "Log into Your Account",
            URI = "index.php?/auth/login/Ly03M2U2MzA1NjRiMTE0MTBiNzYxYTBmYzk4ODg0ZGFjMWFmMjg1ZjZlMjA1YTAyYWJhNDY0NjgxYWY1YmRkZTA4",
            ID_EMAIL_INPUT = "name",
            ID_PASSWORD_INPUT = "password",
            ID_LOGIN_BUTTON = "button_primary",
            ERROR_MESSAGE_XPATH = "//*[@data-testid='loginErrorText']";

    @Override
    public LoginPage openPage() {
        open(URI);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        $(byText(TITLE_TEXT)).isDisplayed();
        return this;
    }

    public String getErrorMessage() {
        return $x(ERROR_MESSAGE_XPATH).getText();
    }

    public LoginPage login(String email, String password) {
        $(byId(ID_EMAIL_INPUT)).setValue(email);
        $(byId(ID_PASSWORD_INPUT)).setValue(password);
        return this;
    }

    public DashboardPage clickLogInButton() {
        $(byId(ID_LOGIN_BUTTON)).click();
        return new DashboardPage();
    }
}
