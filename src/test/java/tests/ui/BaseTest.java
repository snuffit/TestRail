package tests.ui;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import steps.api.ProjectAPIStep;
import steps.ui.DashboardStep;
import steps.ui.LoginStep;
import utils.PropertyReader;
import utils.TestListener;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    LoginStep loginStep;
    LoginPage loginPage;
    DashboardStep dashboardStep;
    DashboardPage dashboardPage;
    ProjectAPIStep projectAPIstep;
    String email = System.getProperty("email", PropertyReader.getProperty("email"));
    String password = System.getProperty("password", PropertyReader.getProperty("password"));
    String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Open browser")
    public void setup(@Optional("chrome") String browser) {
        log.info("Setting up browser: {}", browser);
        Configuration.baseUrl = baseURL;
        Configuration.timeout = 10000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = null;
        if(browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            if (System.getProperty("headless", "true").equals("true")) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        }else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            if (System.getProperty("headless", "true").equals("true")) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
        }
        WebDriverRunner.setWebDriver(driver);
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
        if(WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
