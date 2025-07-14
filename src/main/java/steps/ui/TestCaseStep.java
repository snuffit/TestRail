package steps.ui;

import dto.test_case.TestCase;
import io.qameta.allure.Step;
import pages.ProjectPage;
import pages.TestCasePage;

public class TestCaseStep {

    @Step("Create Test Case")
    public TestCasePage createTestCase(String projectName, TestCase testCase) {
        return new TestCasePage().openPageByName(projectName)
                .isPageOpened()
                .fillFields(testCase)
                .clickAddTestCaseButton()
                .isPageOpened();
    }
}
