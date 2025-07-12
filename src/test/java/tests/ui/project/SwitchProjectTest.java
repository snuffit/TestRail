package tests.ui.project;

import com.github.javafaker.Faker;
import dto.project.Project;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.ui.BaseTest;

import static org.testng.Assert.assertEquals;

public class SwitchProjectTest extends BaseTest {

    Project firstProject = Project.builder()
            .name(new Faker().name().name())
            .build();
    Project secondProject = Project.builder()
            .name(new Faker().name().name())
            .build();

    @BeforeMethod(description = "Create two projects")
    public void createTwoProjects() {
        projectAPIstep.createProject(firstProject);
        projectAPIstep.createProject(secondProject);
    }

    @Epic("Switch project")
    @Feature("Project")
    @Story("Switch project")
    @Test(testName = "Switch project")
    public void switchProject() {
        loginStep.auth(email, password);
        dashboardPage.openProject(firstProject.getName());
        projectPage.switchProjectTo(secondProject.getName());
        assertEquals(projectPage.getProjectName(), secondProject.getName());
    }

    @AfterMethod(description = "Delete projects")
    public void deleteProjects() {
        projectAPIstep.deleteProject(firstProject.getName());
        projectAPIstep.deleteProject(secondProject.getName());
    }
}
