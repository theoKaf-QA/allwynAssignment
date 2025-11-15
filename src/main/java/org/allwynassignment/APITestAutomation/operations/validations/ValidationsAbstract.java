package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ValidationsAbstract<T>{

    @Step("Verify response status code is {expectedStatusCode}")
    public void verifyStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode != expectedStatusCode) {
            log.error("Expected status code {} but got status code {}", expectedStatusCode, actualStatusCode);
            throw new AssertionError(
                    String.format("Expected status code %d but got %d. Response: %s",
                            expectedStatusCode, actualStatusCode, response.asString())
            );
        }
    }

    @Step("Verify status code is a 2xx success one")
    public void verifySuccessfulStatusCode200(Response response) {
        int statusCode = response.getStatusCode();
        if (statusCode < 200 || statusCode >= 300) {
            log.error("Expected status code 200 but got status code {}", statusCode);
            throw new AssertionError(
                    String.format("Expected success status code 2xx but got %d", statusCode)
            );
        }
    }

    @Step("Verify status code indicates errors, either 4xx or 5xx")
    public void verifyErrorStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        if (statusCode < 400) {
            log.error("Expected status code 4xx or 5xx but got status code {}", statusCode);
            throw new AssertionError(
                    String.format("Expected error status code (4xx or 5xx) but got %d", statusCode)
            );
        }
    }
}
