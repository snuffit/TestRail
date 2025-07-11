package tests.ui;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import steps.api.ProjectAPIStep;
import steps.ui.DashboardStep;
import steps.ui.LoginStep;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    LoginStep loginStep;
    LoginPage loginPage;
    DashboardStep dashboardStep;
    DashboardPage dashboardPage;
    ProjectAPIStep projectAPIstep;
    Dotenv dotenv = Dotenv.load();;
    String email = System.getProperty("email", dotenv.get("EMAIL"));
    String password = System.getProperty("password", dotenv.get("PASSWORD"));
    String baseURL = System.getProperty("baseURL", dotenv.get("BASE_URL"));

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Open browser")
    public void setup(@Optional("default") String browserFromTestNG) {
        String browser = System.getProperty("browser",
                !browserFromTestNG.equals("default") ? browserFromTestNG : "chrome");
        if(browser.equals("chrome")) {
            Configuration.browser = "chrome";
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            Configuration.browserCapabilities = options;
        }else if (browser.equals("firefox")) {
            Configuration.browser = "firefox";
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            Configuration.browserCapabilities = options;
        }
        Configuration.baseUrl = baseURL;
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = null;
        if(System.getProperty("headless", "true").equals("true")){
            Configuration.headless = true;
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
        loginStep = new LoginStep();
        loginPage = new LoginPage();
        dashboardStep = new DashboardStep();
        dashboardPage = new DashboardPage();
        projectAPIstep = new ProjectAPIStep();
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        getWebDriver().quit();
    }
}
