package tests.ui.milestone;

import com.github.javafaker.Faker;
import dto.milestone.Milestone;
import dto.milestone.MilestoneFactory;
import dto.project.Project;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

public class CreateMilestoneTest extends BaseTest {

    Project testProject = Project.builder()
            .name(new Faker().name().name())
            .build();

    @BeforeMethod(description = "Create project for test")
    public void createProject() {
        projectAPIstep.createProject(testProject);
    }

    @Test(testName = "Check that it is possible to create a new Milestone.")
    public void checkCreateMilestoneTest() {
        Milestone milestone = MilestoneFactory.getMilestone();
        loginStep.auth(email, password);
        dashboardPage.openProject(testProject.getName())
                .openMilestonesPage()
                .clickAddMilestone()
                .createNewMilestone(milestone);
        Assert.assertTrue(milestonePage.isSuccessMessageVisible());
    }

    @AfterMethod(description = "Delete project")
    public void deleteProject() {
        projectAPIstep.deleteProject(testProject.getName());
    }
}
