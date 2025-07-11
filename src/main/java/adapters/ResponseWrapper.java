package adapters;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ResponseWrapper {
    private Response response;

    public ResponseWrapper(Response response) {
        this.response = response;
        logAndAttachResponse();
    }

    private void logAndAttachResponse() {
        String body = response.asPrettyString();
        int status = response.getStatusCode();
        String contentType = response.getContentType();

        log.info("Response Status: {}", status);
        log.info("Response Content-Type: {}", contentType);
        log.info("Response Body:\n{}", body);

        Allure.addAttachment("Response Status", String.valueOf(status));
        Allure.addAttachment("Response Content-Type", contentType);
        Allure.addAttachment("Response Body", "application/json", body, ".json");
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public Response getResponse() {
        return response;
    }

    public ResponseWrapper haveStatusCode(int expectedStatusCode) {
        assertEquals(getStatusCode(), expectedStatusCode, "Ожидаемый статус " + expectedStatusCode +
                " не получен, получен: " + getStatusCode());
        return this;
    }
}
