package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public abstract class ValidationsAbstract<T>{

    @Step("Verify response status code is {expectedStatusCode}")
    public void verifyStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertThat(expectedStatusCode)
                .as("Verify response status code is {}", expectedStatusCode)
                .isEqualTo(actualStatusCode);
    }

    @Step("Verify status code is a 2xx success one")
    public void verifySuccessfulStatusCode200(Response response) {
        int statusCode = response.getStatusCode();
        assertThat(statusCode)
                .as("Verify response status code is 2xx")
                .isBetween(200, 299);
    }

    @Step("Verify status code indicates errors, either 4xx or 5xx")
    public void verifyErrorStatusCode(Response response) {
        int statusCode = response.getStatusCode();
        assertThat(statusCode)
                .as("Verify response status code is 4xx or 5xx")
                .isGreaterThanOrEqualTo(400);
    }
}
