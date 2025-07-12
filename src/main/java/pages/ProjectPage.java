package pages;

import lombok.extern.log4j.Log4j2;
import steps.api.ProjectAPIStep;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectPage extends BasePage{

    final String URI = "index.php?/projects/overview/%s",
    PROJECTS_ID_CLASS = "content-header-id",
    PROJECT_CHEVRON_CLASS = "//*[@class='project-avatar ']",
    PROJECT_SEARCH_XPATH = "//*[@class='projects_search']//a[text()='%s']",
    PROJECT_TITLE_XPATH = "//*[@data-testid='testCaseContentHeaderTitle']";

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
}
