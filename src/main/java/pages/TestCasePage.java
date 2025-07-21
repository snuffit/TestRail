package pages;

import dto.test_case.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import steps.api.ProjectAPIStep;
import wrappers.Dropdown;
import wrappers.Input;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestCasePage extends BasePage {

    final String BASE_URL = "/index.php?/",
            ADD_TEST_CASE_URI = BASE_URL + "cases/add/%s&referer=projects/overview/%s",
            TITLE_XPATH = "//*[@data-testid='testCaseContentHeaderTitle']",
            ADD_TEST_CASE_BUTTON_XPATH = "//button[@id='accept']",
            ALL_TEST_CASES_PAGE_URI =
                    BASE_URL + "suites/view/%s&group_by=cases:section_id&group_order=asc&display_deleted_cases=0",
            TEST_CASE_TITLE_XPATH = "//span[@data-testid='sectionCaseTitle' and text()='%s']",
            SUCCESS_MESSAGE_TITLE_XPATH = "//div[@data-testid='messageSuccessDivBox']",
            TEST_CASE_TITLE = "Title",
            TEST_CASE_PILL = "//table//tr//td//a[contains(., '%s')]";


    public TestCasePage openPageByName(String projectName) {
        log.info("Open the Test Case Page of the '{}' project", projectName);
        String projectId = new ProjectAPIStep().getProjectID(projectName);
        open(String.format(ADD_TEST_CASE_URI, projectId, projectId));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public TestCasePage isPageOpened() {
        $x(TITLE_XPATH).isDisplayed();
        log.info("Test Case page is opened");
        return this;
    }

    public TestCasePage fillFields(TestCase testCase) {
        log.info("Fill fields in Test Case with title '{}'", testCase.getTitle());
        new Input("Title").write(testCase.getTitle());
        new Dropdown("Template").select(testCase.getTemplate());
        new Dropdown("Type").select(testCase.getType());
        new Dropdown("Priority").select(testCase.getPriority());
        new Input("Estimate").write(testCase.getEstimate());
        return this;
    }

    public TestCasePage clickAddTestCaseButton() {
        log.info("Click Add Test Case Button");
        $x(ADD_TEST_CASE_BUTTON_XPATH).click();
        return this;
    }

    public TestCasePage openAllTestCasesPage(String projectName) {
        log.info("Open 'All Test Cases' Page of the '{}' project", projectName);
        String projectId = new ProjectAPIStep().getProjectID(projectName);
        open(String.format(ALL_TEST_CASES_PAGE_URI, projectId, projectId));
        return this;
    }

    public boolean isTestCaseCreated(String testCaseName) {
        return $x(String.format(TEST_CASE_TITLE_XPATH, testCaseName)).should(visible).isDisplayed();
    }

    @Step("Get title for the 'Test Case' page")
    public String getSuccessMessage() {
        log.info("Get title for the 'Test Case' page");
        return $x(SUCCESS_MESSAGE_TITLE_XPATH).getText();
    }

    @Step("Editing the test case.")
    public TestCasePage openEditTestCase(String testCase) {
        log.info("Editing the '{}' test case.", testCase);
        $x(String.format(TEST_CASE_PILL, testCase)).hover().click();
        return this;
    }

    @Step("Edit title for Test Case.")
    public TestCasePage updateTitleTestCase(String newTestName) {
        log.info("Editing Test case");
        new Input(TEST_CASE_TITLE).write(newTestName);
        clickAddTestCaseButton();
        return this;
    }
}
