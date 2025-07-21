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
import pages.*;
import steps.api.ProjectAPIStep;
import steps.ui.DashboardStep;
import steps.ui.LoginStep;
import steps.ui.TestCaseStep;
import utils.PropertyReader;
import utils.TestListener;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    protected static LoginStep loginStep;
    protected static LoginPage loginPage;
    protected static MilestonePage milestonePage;
    protected static AddMilestonePage addMilestonePage;
    protected static ProjectPage projectPage;
    protected static TestCaseStep testCaseStep;
    protected static TestCasePage testCasePage;
    protected static DashboardStep dashboardStep;
    protected static DashboardPage dashboardPage;
    protected static ProjectAPIStep projectAPIstep;
    protected static String email = System.getProperty("email", PropertyReader.getProperty("email"));
    protected static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    protected static String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));
    protected static WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Open browser")
    public void setup(@Optional("chrome") String browser) {
        log.info("Setting up browser: {}", browser);
        Configuration.baseUrl = baseURL;
        Configuration.timeout = 20000;
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
        milestonePage = new MilestonePage();
        addMilestonePage = new AddMilestonePage();
        projectPage = new ProjectPage();
        dashboardStep = new DashboardStep();
        dashboardPage = new DashboardPage();
        projectAPIstep = new ProjectAPIStep();
        testCaseStep = new TestCaseStep();
        testCasePage = new TestCasePage();
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        if(WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
