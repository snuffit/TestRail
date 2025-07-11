package pages;

import dto.project.Project;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage extends BasePage {

    private final String URI = "index.php?/dashboard",
            ID_ADD_PROJECT_BUTTON = "sidebar-projects-add",
            ID_PROJECT_NAME_INPUT = "name",
            ID_ANNOUNCEMENT_TEXTAREA = "announcement_display",
            ID_SHOW_ANNOUNCEMENT_RADIOBUTTON = "show_announcement",
            ID_ENABLE_APPROVALS = "case_statuses_enabled",
            ID_ACCEPT_CREATE_PROJECT_BUTTON = "accept",
            XPATH_PROJECT_NAME = "//*[@id='content_container']//a[text()='%s']",
            PAGE_TITLE = "Dashboard";

    @Override
    public DashboardPage openPage() {
        open(URI);
        return this;
    }

    @Override
    public DashboardPage isPageOpened() {
        $(byText(PAGE_TITLE)).isDisplayed();
        return this;
    }

    public DashboardPage clickAddProjectButton() {
        $(byId(ID_ADD_PROJECT_BUTTON)).click();
        return this;
    }

    public DashboardPage fillProjectFields(Project project) {
        $(byId(ID_PROJECT_NAME_INPUT)).setValue(project.getName());
        $(byId(ID_ANNOUNCEMENT_TEXTAREA)).setValue(project.getAnnouncement());
        if (project.isShowAnnouncement()) {
            $(byId(ID_SHOW_ANNOUNCEMENT_RADIOBUTTON)).click();
        }
        $(byId(project.getSuiteMode())).click();
        if (project.isEnableApprovals()) {
            $(byId(ID_ENABLE_APPROVALS)).click();
        }
        return this;
    }

    public DashboardPage clickAcceptButton() {
        $(byId(ID_ACCEPT_CREATE_PROJECT_BUTTON)).click();
        return this;
    }

    public boolean isProjectNameVisible(String projectName) {
        return $x(String.format(XPATH_PROJECT_NAME, projectName)).isDisplayed();
    }
}
