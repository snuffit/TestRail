package adapters;

import dto.models.get_projects_rs.GetProjectsRs;
import dto.models.get_projects_rs.Project;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectAPI extends BaseApi {

    static RequestSpecification spec =
            given()
                    .auth().preemptive().basic(email, password)
                    .contentType(ContentType.JSON);

    public void deleteProjectAPI(String projectName) {
        spec
                .when()
                .post(BASE_PATH + "delete_project/" + getProjectID(projectName))
                .then()
                .log().all()
                .statusCode(200);
    }

    private Integer getProjectID(String projectName) {
        GetProjectsRs rs =
                spec
                        .when()
                        .get(BASE_PATH + "get_projects")
                        .then()
                        .log().all()
                        .extract()
                        .as(GetProjectsRs.class);
        List<Project> projects = rs.getProjects();
        for (Project project : projects) {
            if (project.name.equals(projectName)) {
                return project.id;
            }
        }
        return null;
    }
}
