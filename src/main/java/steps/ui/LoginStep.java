package steps.ui;

import pages.LoginPage;

public class LoginStep {

    public void auth(String email, String password) {
        new LoginPage().openPage()
                .isPageOpened()
                .login(email, password)
                .clickLogInButton()
                .isPageOpened();
    }
}
