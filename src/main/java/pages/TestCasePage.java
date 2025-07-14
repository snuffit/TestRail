package pages;

import dto.test_case.TestCase;
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
            TEST_CASE_TITLE_XPATH = "//span[@data-testid='sectionCaseTitle' and text()='%s']";


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
//        new Dropdown("Assigned To").select(testCase.getAssignedTo());
//        new Dropdown("Automation Type").select(testCase.getAutomationType());
        new Input("Estimate").write(testCase.getEstimate());
//        new Textarea("References").write(testCase.getReferences());
//        new Textarea("Preconditions").write(testCase.getPreconditions());
//        new Textarea("Steps").write(testCase.getSteps());
//        new Textarea("Expected Result").write(testCase.getExpectedResult());
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
}
