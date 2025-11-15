package org.allwynassignment.APITestAutomation.operations.actions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.configuration.ConfigHandler;
import org.allwynassignment.APITestAutomation.models.Author;
import org.allwynassignment.APITestAutomation.operations.BaseOperations;

public class AuthorActions extends BaseOperations implements ActionsInterface<Author> {
    private final static ConfigHandler configHandlerInstance = ConfigHandler.getInstance();

    public AuthorActions() {
        super(configHandlerInstance.getAuthorsEndpoint());
    }

    @Step("Get all authors in system")
    @Override
    public Response getAll() {
        return webClient.get(endpoint);
    }

    @Step("Get author by ID: {id}")
    @Override
    public Response getByID(int id) {
        return webClient.get(buildEndpoint(id));
    }

    @Step("Add a new author in system")
    @Override
    public Response add(Author author) {
        return webClient.post(endpoint, author);
    }

    @Step("Update author with ID: {id}")
    @Override
    public Response update(int id, Author author) {
        return webClient.put(buildEndpoint(id), author);
    }

    @Step("Delete author with ID: {id}")
    @Override
    public Response delete(int id) {
        return webClient.delete(buildEndpoint(id));
    }

    public Response getAllAuthors() {
        return getAll();
    }

    public Response getAuthorById(int id) {
        return getByID(id);
    }

    public Response addAuthor(Author author) {
        return add(author);
    }

    public Response updateAuthor(int id, Author author) {
        return update(id, author);
    }

    public Response deleteAuthor(int id) {
        return delete(id);
    }
}
