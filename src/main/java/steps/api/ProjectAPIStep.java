package steps.api;

import adapters.BaseApi;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Log4j2
public class ProjectAPIStep extends BaseApi {

    final String BASE_URI = "index.php?/api/v2/";

    public Response deleteProjectAPI(String projectName) {
        String id = getProjectID(projectName);
        log.info("Delete project with id '{}' by API", id);
        return post( BASE_URI + "delete_project/" + id).getResponse();
    }

    private String getProjectID(String projectName) {
        log.info("Get projects by API");
        Response response = get(BASE_URI + "get_projects").getResponse();
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
}
