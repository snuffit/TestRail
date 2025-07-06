package tests.ui;

import adapters.BaseApi;
import adapters.ProjectAPI;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;
import steps.DashboardStep;
import steps.LoginStep;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    LoginStep loginStep;
    LoginPage loginPage;
    DashboardStep dashboardStep;
    DashboardPage dashboardPage;
    BaseApi baseApi;
    ProjectAPI projectAPI;
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
        loginStep = new LoginStep();
        loginPage = new LoginPage();
        dashboardStep = new DashboardStep();
        dashboardPage = new DashboardPage();
        baseApi = new BaseApi();
        projectAPI = new ProjectAPI();
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
