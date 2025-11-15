package org.allwynassignment.APITestAutomation.operations.actions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.configuration.ConfigHandler;
import org.allwynassignment.APITestAutomation.models.Book;
import org.allwynassignment.APITestAutomation.operations.BaseOperations;

public class BookActions extends BaseOperations implements ActionsInterface<Book>{
    private final static ConfigHandler configHandlerInstance = ConfigHandler.getInstance();
    
    public BookActions() {
        super(configHandlerInstance.getBooksEndpoint());
    }

    @Step("Get all books from system")
    @Override
    public Response getAll() {
        return webClient.get(endpoint);
    }

    @Step("Get book by ID: {id}")
    @Override
    public Response getByID(int id) {
        return webClient.get(buildEndpoint(id));
    }

    @Step("Add new book in system")
    @Override
    public Response add(Book book) {
        return webClient.post(endpoint, book);
    }

    @Step("Update book with ID: {id}")
    @Override
    public Response update(int id, Book book) {
        return webClient.put(buildEndpoint(id), book);
    }

    @Step("Delete book with ID: {id}")
    @Override
    public Response delete(int id) {
        return webClient.delete(buildEndpoint(id));
    }

    public Response getAllBooks() {
        return getAll();
    }

    public Response getBookById(int id) {
        return getByID(id);
    }

    public Response createBook(Book book) {
        return add(book);
    }

    public Response updateBook(int id, Book book) {
        return update(id, book);
    }

    public Response deleteBook(int id) {
        return delete(id);
    }
}
