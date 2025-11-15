package org.allwynassignment.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.models.Author;
import org.allwynassignment.APITestAutomation.utils.AuthorDataFactory;
import org.testng.annotations.Test;

@Epic("Allwyn Assignment Test Automation")
@Owner("Theodoros Kafazis")
@Feature("Authors FakeRestAPI Tests")
public class AuthorsTests extends BaseTest {

    @Test(priority = 1, description = "Verify GET all authors returns 200 and list of authors",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /Authors endpoint returns successful response")
    public void testGetAllAuthors_Success() {
        Response response = authorActions.getAllAuthors();

        authorValidations.verifyStatusCode(response, 200);

        Author[] authors = authorValidations.parseListResponse(response);
        authorValidations.verifyListNotEmpty(authors);

        Author firstAuthor = authors[0];
        authorValidations.verifyHasValidID(firstAuthor);
    }

    @Test(priority = 2, description = "Verify GET author by valid ID returns 200 and author details",
    groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /Authors/{id} endpoint returns author details")
    public void testGetAuthorById_ValidId_Success() {
        int validAuthorId = 1;

        Response response = authorActions.getAuthorById(validAuthorId);

        authorValidations.verifySuccessfulStatusCode200(response);

        Author author = authorValidations.parseResponse(response);
        authorValidations.verifyNotNull(author);
        authorValidations.verifyAuthorID(author, validAuthorId);
    }

    @Test(priority = 3, description = "Verify POST creates a new author successfully",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that POST /Authors endpoint creates a new author")
    public void testCreateAuthor_ValidData_Success() {
        Author newAuthor = AuthorDataFactory.createValidAuthor();

        Response response = authorActions.addAuthor(newAuthor);

        authorValidations.verifyStatusCode(response, 200);

        Author createdAuthor = authorValidations.parseResponse(response);
        authorValidations.verifyNotNull(createdAuthor);
        authorValidations.verifyAuthorID(createdAuthor, createdAuthor.getId());
        authorValidations.verifyAuthorIDBook(createdAuthor, createdAuthor.getIdBook());
        authorValidations.verifyAuthorFirstName(createdAuthor, newAuthor.getFirstName());
        authorValidations.verifyAuthorLastName(createdAuthor, newAuthor.getLastName());
    }


    @Test(priority = 4, description = "Verify PUT updates an existing author successfully",
             groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that PUT /Authors/{id} endpoint updates an existing author")
    public void testUpdateAuthor_ValidData_Success() {
        Author updatedAuthor = AuthorDataFactory.updateValidAuthor(
                Author.builder()
                        .id(30)
                        .idBook(100)
                        .firstName("Test Name")
                        .lastName("Test Last Name")
                        .build()
        );

        Response response = authorActions.updateAuthor(30, updatedAuthor);

        authorValidations.verifyStatusCode(response, 200);

        Author responseAuthor = authorValidations.parseResponse(response);
        authorValidations.verifyNotNull(responseAuthor);
        authorValidations.verifyAuthorID(responseAuthor, updatedAuthor.getId());
        authorValidations.verifyAuthorIDBook(responseAuthor, updatedAuthor.getIdBook());
        authorValidations.verifyAuthorFirstName(responseAuthor, updatedAuthor.getFirstName());
        authorValidations.verifyAuthorLastName(responseAuthor, updatedAuthor.getLastName());
    }

    @Test(priority = 5, description = "Verify DELETE removes an author successfully",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that DELETE /Authors/{id} endpoint deletes an author")
    public void testDeleteAuthor_ValidId_Success() {
        int authorId = 1;
        Response response = authorActions.deleteAuthor(authorId);
        authorValidations.verifyStatusCode(response, 200);
    }


    @Test(priority = 6, description = "Verify GET author with noexistent ID returns 404",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that GET /Authors/{id} returns 404 for nonexistent ID")
    public void testGetAuthorById_NonExistentId_ReturnsNotFound() {
        int nonExistentId = 999999;
        Response response = authorActions.getAuthorById(nonExistentId);
        authorValidations.verifyStatusCode(response, 404);
    }

    @Test(priority = 7, description = "Verify GET author with zero ID",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when requesting author with ID = 0")
    public void testGetAuthorById_ZeroId() {
        int zeroId = 0;
        Response response = authorActions.getAuthorById(zeroId);
        authorValidations.verifyErrorStatusCode(response);
    }

    @Test(priority = 8, description = "Verify GET author with negative ID",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when requesting author with negative ID")
    public void testGetAuthorById_NegativeId() {
        int negativeId = -1;
        Response response = authorActions.getAuthorById(negativeId);
        authorValidations.verifyErrorStatusCode(response);
    }

    @Test(priority = 9, description = "Verify POST author with minimal data",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that POST /Authors works with minimal required fields")
    public void testCreateAuthor_MinimalData_Success() {
        Author minimalAuthor = AuthorDataFactory.createMinimalAuthor();
        Response response = authorActions.addAuthor(minimalAuthor);
        authorValidations.verifyStatusCode(response, 200);
    }

    @Test(priority = 10, description = "Verify POST author with empty body",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when creating author with empty body")
    public void testCreateAuthor_EmptyBody() {
        Author emptyAuthor = Author.builder().build();
        Response response = authorActions.addAuthor(emptyAuthor);
        authorValidations.verifySuccessfulStatusCode200(response);
    }

    @Test(priority = 11, description = "Verify PUT author with negative ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when updating negative author")
    public void testUpdateAuthor_negativeID_Success() {
        int negativeID = -1;
        Author author = AuthorDataFactory.createValidAuthor();
        Response response = authorActions.updateAuthor(negativeID, author);
        authorValidations.verifySuccessfulStatusCode200(response);
    }

    @Test(priority = 12, description = "Verify DELETE author with negative ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when deleting negative author")
    public void testDeleteAuthor_negativeID_Success() {
        int negativeID = -1;
        Response response = authorActions.deleteAuthor(negativeID);
        authorValidations.verifySuccessfulStatusCode200(response);
    }

}
