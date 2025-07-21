package tests.ui.test_case;

import com.github.javafaker.Faker;
import dto.project.Project;
import dto.test_case.TestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

import static org.testng.Assert.assertEquals;

public class EditTestCaseTest extends BaseTest {

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

    @Test(testName = "Check Test Case editing.")
    public void checkEditTestCaseTest() {
    loginStep.auth(email, password);
    testCaseStep.createTestCase(testProject.getName(), testCase);
    testCasePage.openEditTestCase(testCase.getTitle());
    testCasePage.updateTitleTestCase(testCase.getTitle().concat("New"));
    assertEquals(testCasePage.getSuccessMessage(),  "Successfully updated the test case.");
    }

    @AfterMethod(description = "Delete project")
    public void deleteProject() {
        projectAPIstep.deleteProject(testProject.getName());
    }
}
