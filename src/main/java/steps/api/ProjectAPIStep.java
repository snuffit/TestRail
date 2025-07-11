package steps.api;

import adapters.BaseApi;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectAPIStep extends BaseApi {

    final String BASE_URI = "index.php?/api/v2/";

    public Response deleteProjectAPI(String projectName) {
        String id = getProjectID(projectName);
        return post( BASE_URI + "delete_project/" + id).getResponse();
    }

    private String getProjectID(String projectName) {
        Response response = get(BASE_URI + "get_projects").getResponse();
        String keyName = "name";
        String keyId = "id";
        List<Map<String, Object>> projects = response.jsonPath().getList("projects");
        for (Map<String, Object> project : projects) {
            if (project.get(keyName).toString().contains(projectName)) {
                return project.get(keyId).toString();
            }
        }
        return null;
    }
}
