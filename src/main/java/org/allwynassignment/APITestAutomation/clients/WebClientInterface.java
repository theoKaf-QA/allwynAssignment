package org.allwynassignment.APITestAutomation.clients;

import io.restassured.response.Response;

public interface WebClientInterface {
    Response get(String endpoint);
    Response post(String endpoint, Object body);
    Response put(String endpoint, Object body);
    Response delete(String endpoint);
}
