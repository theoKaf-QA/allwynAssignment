package org.allwynassignment.APITestAutomation.operations.actions;

import io.restassured.response.Response;

public interface ActionsInterface<T> {

    Response getAll();
    Response getByID(int id);
    Response add(T entity);
    Response update(int id, T entity);
    Response delete(int id);

}
