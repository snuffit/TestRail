package tests.ui;

import dto.project.Project;
import dto.project.SuiteMode;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateProjectTest extends BaseTest {

    String projectName = "JustTest";

    @Test
    public void createProject() {
        Project project = Project.builder()
                .name(projectName)
                .announcement("description")
                .suiteMode(SuiteMode.SINGLE_FOR_ALL_CASES.getSuiteMode())
                .isEnableApprovals(true)
                .isShowAnnouncement(false)
                .build();
        loginStep.auth(email, password);
        dashboardStep.createProject(project);
        assertThat(dashboardPage.getProjectName())
                .isEqualTo(projectName);
    }

    @AfterMethod
    public void deleteProjectByAPI() {
        projectAPI.deleteProjectAPI(projectName);
    }
}
