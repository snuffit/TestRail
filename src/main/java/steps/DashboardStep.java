package steps;

import dto.project.Project;
import pages.DashboardPage;

public class DashboardStep {

    public void createProject(Project project) {
        new DashboardPage()
                .isPageOpened()
                .clickAddProjectButton()
                .fillProjectFields(project)
                .clickAcceptButton();
    }
}
