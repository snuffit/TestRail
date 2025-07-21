package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import steps.api.ProjectAPIStep;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectPage extends BasePage{

    final String URI = "index.php?/projects/overview/%s",
    PROJECTS_ID_CLASS = "content-header-id",
    PROJECT_CHEVRON_CLASS = "//*[@class='project-avatar ']",
    PROJECT_SEARCH_XPATH = "//*[@class='projects_search']//a[text()='%s']",
    PROJECT_TITLE_XPATH = "//*[@data-testid='testCaseContentHeaderTitle']",
    ADD_TEST_CASE_BUTTON_XPATH = "//*[@data-testid='sidebarCasesAdd']",
    TEST_CASE_TITLE_XPATH = "//span[@data-testid='sectionCaseTitle' and text()='%s']",
            MILESTONES_BUTTON_XPATH = "//div[@class='sidebar-layout-content']" +
                    "/descendant::a[starts-with(normalize-space(.), 'Milestone')]";

    public ProjectPage openPageByName(String projectName) {
        log.info("Open project '{}' page", projectName);
        String id = new ProjectAPIStep().getProjectID(projectName);
        open(String.format(URI, id));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Override
    public ProjectPage isPageOpened() {
        $(PROJECTS_ID_CLASS).isDisplayed();
        log.info("Project page is opened");
        return this;
    }

    public ProjectPage switchProjectTo(String projectName) {
        $x(PROJECT_CHEVRON_CLASS).click();
        $x(String.format(PROJECT_SEARCH_XPATH,projectName)).click();
        log.info("Switch to project '{}'", projectName);
        return this;
    }

    public String getProjectName() {
        return $x(PROJECT_TITLE_XPATH).getText();
    }

    public TestCasePage clickAddTestCaseButton() {
        $x(ADD_TEST_CASE_BUTTON_XPATH).click();
        return new TestCasePage();
    }

    @Step("Opening the 'Milestone' page for the project.")
    public MilestonePage openMilestonesPage() {
        log.info("Opening the 'Milestone' page for the project.");
        $x(MILESTONES_BUTTON_XPATH).click();
        return new MilestonePage();
    }
}
