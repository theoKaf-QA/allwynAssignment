package org.allwynassignment.APITestAutomation.operations.validations;

import io.restassured.response.Response;

public interface ValidationInterface<T> {

    T parseResponse(Response response);
    T[] parseListResponse(Response response);
    void verifyStatusCode(Response response, int expectedStatusCode);
    void verifyNotNull(T entity);
    void verifyIsNull(T entity);
    void verifyListNotEmpty(T[] entities);
    void verifyListIsEmpty(T[] entities);
    void verifyHasValidID(T entity);
}
