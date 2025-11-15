package org.allwynassignment.APITestAutomation.operations;

import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.clients.FakeRestAPIClient;
import org.allwynassignment.APITestAutomation.clients.WebClientInterface;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseOperations {
    protected final WebClientInterface webClient;
    protected final String endpoint;


    public BaseOperations(String endpoint) {
        this.webClient = new FakeRestAPIClient();
        this.endpoint = endpoint;
    }

    protected String buildEndpoint(int id) {
        return new StringBuilder()
                .append(endpoint)
                .append("/")
                .append(id)
                .toString();
    }

    protected void validateResponse(Response response, int expectedStatusCode) {

        assertThat(response.getStatusCode())
                .as("Expected status code {} but got {}", expectedStatusCode, response.getStatusCode())
                .isNotNull()
                .isEqualTo(expectedStatusCode);
    }
}
