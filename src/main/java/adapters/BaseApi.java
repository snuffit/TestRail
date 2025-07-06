package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.cdimascio.dotenv.Dotenv;

public class BaseApi {

    static String email = Dotenv.load().get("EMAIL");
    static String password = Dotenv.load().get("PASSWORD");
    final String BASE_PATH = "https://pstyhaproject.testrail.io/index.php?/api/v2/";

    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create();

//    public BaseApi() {
//        RestAssured.baseURI = "https://pstyhaproject.testrail.io/index.php?/api/v2/";
//    }
}
