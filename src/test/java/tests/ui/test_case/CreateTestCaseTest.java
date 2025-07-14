package tests.ui.test_case;

import com.github.javafaker.Faker;
import dto.project.Project;
import dto.test_case.TestCase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

public class CreateTestCaseTest extends BaseTest {

    Project testProject = Project.builder()
            .name(new Faker().name().name())
            .build();
    TestCase testCase = TestCase.builder()
            .title(new Faker().app().name())
            .build();

    @BeforeMethod(description = "Create project for test")
    public void createProject() {
        projectAPIstep.createProject(testProject);
    }

    @Epic("Create Test Case")
    @Feature("Test Case")
    @Story("Create Test Case")
    @Test(testName = "Create Test Case")
    public void createTestCase() {
        loginStep.auth(email, password);
        testCaseStep.createTestCase(testProject.getName(), testCase);
        testCasePage.openAllTestCasesPage(testProject.getName());
        Assert.assertTrue(testCasePage.isTestCaseCreated(testCase.getTitle()));
    }

    @AfterMethod(description = "Delete project")
    public void deleteProject() {
        projectAPIstep.deleteProject(testProject.getName());
    }
}
