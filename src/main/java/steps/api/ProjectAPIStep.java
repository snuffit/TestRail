package steps.api;

import adapters.BaseApi;
import dto.project.Project;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectAPIStep extends BaseApi {

    final String BASE_URI = "index.php?/api/v2/",
    DELETE_PROJECT_ENDPOINT = "delete_project/",
    GET_PROJECT_ENDPOINT = "get_projects",
    CREATE_PROJECT_ENDPOINT = "add_project";

    @Step("Delete project")
    public Response deleteProject(String projectName) {
        String id = getProjectID(projectName);
        log.info("Delete project with id '{}' by API", id);
        return post( BASE_URI + DELETE_PROJECT_ENDPOINT + id).getResponse();
    }

    @Step("Get project id")
    public String getProjectID(String projectName) {
        log.info("Get projects by API");
        Response response = get(BASE_URI + GET_PROJECT_ENDPOINT).getResponse();
        String keyName = "name";
        String keyId = "id";
        List<Map<String, Object>> projects = response.jsonPath().getList("projects");
        for (Map<String, Object> project : projects) {
            if (project.get(keyName).toString().contains(projectName)) {
                String id = project.get(keyId).toString();
                log.info("Project with name '{}' has id '{}'", projectName, id);
                return id;
            }
        }
        return null;
    }

    @Step("Create project")
    public void createProject(Project project) {
        log.info("Create project with name '{}'", project.getName());
        Map<String, Object> body = Map.of(
            "name", project.getName(),
                "announcement", project.getAnnouncement(),
                "show_announcement", project.isShowAnnouncement(),
                "suite_mode",  getSuiteModeNum(project.getSuiteMode())
        );
        post(BASE_URI + CREATE_PROJECT_ENDPOINT, body);
    }

    private Integer getSuiteModeNum(String suiteMode) {
        switch (suiteMode) {
            case "suite_mode_single" -> {
                return 1;
            }
            case "suite_mode_single_baseline" -> {
                return 2;
            }
            case "suite_mode_multi" -> {
                return 3;
            }
        }
        return 0;
    }
}
