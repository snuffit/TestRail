package adapters;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class BaseApi {

    static String email = System.getProperty("email", PropertyReader.getProperty("email"));
    static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    static String baseURL = System.getProperty("baseURL", PropertyReader.getProperty("baseURL"));

    public static RequestSpecification getAuthenticatedSpec() {
        String auth = Base64.getEncoder().encodeToString(
                (email + ":" + password).getBytes(StandardCharsets.UTF_8)
        );
        return new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic " + auth)
                .build();
    }

    public ResponseWrapper get(String endpoint) {
        return new ResponseWrapper(given()
                .spec(getAuthenticatedSpec())
                .get(endpoint)
                .then()
                .extract().response());
    }

    public ResponseWrapper post(String endpoint) {
        return new ResponseWrapper(given()
                .spec(getAuthenticatedSpec())
                .post(endpoint)
                .then()
                .extract().response());
    }
}
