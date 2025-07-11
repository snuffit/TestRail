package tests.ui;

import dto.project.Project;
import dto.project.SuiteMode;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
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
        dashboardPage.openPage();
        assertThat(dashboardPage.isProjectNameVisible(projectName))
                .isTrue();
    }

    @AfterMethod
    public void deleteProjectByAPI() {
        projectAPIstep.deleteProjectAPI(projectName);
    }
}
