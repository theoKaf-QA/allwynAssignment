package org.allwynassignment.APITestAutomation.clients;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.allwynassignment.APITestAutomation.configuration.ConfigHandler;


public class FakeRestAPIClient implements WebClientInterface{
    private final ConfigHandler config;

    public FakeRestAPIClient() {
        this.config = ConfigHandler.getInstance();
        configureRestAssured();
    }

    private void configureRestAssured() {
        RestAssured.baseURI = config.getBaseUri();
        RestAssured.basePath = config.getBasePath();
    }


    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }


    public Response get(String endpoint) {
        return getRequestSpec()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    @Override
    public Response post(String endpoint, Object body) {
        return getRequestSpec()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    @Override
    public Response put(String endpoint, Object body) {
        return getRequestSpec()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    @Override
    public Response delete(String endpoint) {
        return getRequestSpec()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

}
