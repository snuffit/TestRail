package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    LoginPage loginPage;
    Dotenv dotenv;
    String email;
    String password;

    @BeforeMethod
    public void setup() {
        Configuration.baseUrl = "https://pstyhaproject.testrail.io/index.php?/";
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserCapabilities = options;
        loginPage = new LoginPage();
        dotenv = Dotenv.load();
        email = dotenv.get("EMAIL");
        password = dotenv.get("PASSWORD");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @AfterMethod
    public void tearDown() {
        getWebDriver().quit();
    }
}
