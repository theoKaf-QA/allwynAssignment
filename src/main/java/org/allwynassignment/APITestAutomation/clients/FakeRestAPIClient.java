package org.allwynassignment.APITestAutomation.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.configuration.ConfigHandler;

@Slf4j
public class FakeRestAPIClient implements WebClientInterface{
    private final ConfigHandler config;
    private final int timeout;
    private final int maxRetries;
    private final int retryDelayMs;

    public FakeRestAPIClient() {
        this.config = ConfigHandler.getInstance();
        timeout = config.getApiTimeout();
        maxRetries = config.getRetryCount();
        retryDelayMs = config.getRetryInterval();
        configureRestAssured();
    }

    private void configureRestAssured() {
        if(config.getEnvironment().equalsIgnoreCase("test")){
            RestAssured.baseURI = config.getTestUri();
            RestAssured.basePath = config.getTestPath();
        }else {
            RestAssured.baseURI = config.getBaseUri();
            RestAssured.basePath = config.getBasePath();
        }
    }


    private RequestSpecification getRequestSpec() {
        RestAssuredConfig timeoutConfig = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", timeout)
                        .setParam("http.socket.timeout", timeout)
                        .setParam("http.connection-manager.timeout", timeout)
                );

        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .config(timeoutConfig);
    }


    private Response executeWithRetry(RequestExecutor executor) {
        Response response = null;
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxRetries + 1; attempt++) {
            try {
                response = executor.execute();

                if (response.getStatusCode() < 500) {
                    return response;
                }

                if (attempt <= maxRetries) {
                    log.info("Attempt {} failed with status {}. Retrying...", attempt, response.getStatusCode());
                    Thread.sleep(retryDelayMs);
                }

            } catch (Exception e) {
                lastException = e;

                if (attempt <= maxRetries) {
                    log.info("Attempt {} failed with status {}. Retrying...", attempt, response.getStatusCode());
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                } else {
                    log.error("Request failed with {} attempts.", maxRetries + 1);
                    throw new RuntimeException(
                            String.format("Request failed after %d attempts", maxRetries + 1), e);
                }
            }
        }

        if (response != null) {
            return response;
        }

        log.error("Request failed with {} attempts.", maxRetries + 1);
        throw new RuntimeException(
                String.format("Request failed after %d attempts", maxRetries + 1),
                lastException
        );
    }


    public Response get(String endpoint) {
        return executeWithRetry(() ->
                getRequestSpec()
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response()
        );
    }

    @Override
    public Response post(String endpoint, Object body) {
        return executeWithRetry(() ->
                getRequestSpec()
                        .body(body)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract()
                        .response()
        );
    }

    @Override
    public Response put(String endpoint, Object body) {
        return executeWithRetry(() ->
                getRequestSpec()
                        .body(body)
                        .when()
                        .put(endpoint)
                        .then()
                        .extract()
                        .response()
        );
    }

    @Override
    public Response delete(String endpoint) {
        return executeWithRetry(() ->
                getRequestSpec()
                        .when()
                        .delete(endpoint)
                        .then()
                        .extract()
                        .response()
        );
    }


    @FunctionalInterface
    private interface RequestExecutor {
        Response execute() throws Exception;
    }

}
