package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.models.Author;

@Slf4j
public class AuthorValidations extends ValidationsAbstract<Author> implements ValidationInterface<Author> {

    //Interface inherited methods

    @Step("Parse single author from response")
    @Override
    public Author parseResponse(Response response) {
        return response.as(Author.class);
    }

    @Step("Parse authors list from response")
    @Override
    public Author[] parseListResponse(Response response) {
        return response.as(Author[].class);
    }

    @Step("Verify author is not null")
    @Override
    public void verifyNotNull(Author author) {
        if (author == null) {
            log.error("Author should not be null");
            throw new AssertionError("Author should not be null");
        }
    }

    @Step("Verify author is null")
    @Override
    public void verifyIsNull(Author author) {
        if (author != null) {
            log.error("Author should be null");
            throw new AssertionError("Author should be null");
        }
    }

    @Step("Verify authors list is not empty")
    @Override
    public void verifyListNotEmpty(Author[] authors) {
        if (authors == null) {
            log.error("Author list should not be null");
            throw new AssertionError("Authors list should not be null");
        }
        if (authors.length == 0) {
            log.error("Author list should not be empty");
            throw new AssertionError("Authors list should not be empty");
        }
    }

    @Step("Verify authors list is empty")
    @Override
    public void verifyListIsEmpty(Author[] authors) {
        if (authors == null) {
            log.error("Author list should not be null");
            throw new AssertionError("Authors list should not be null");
        }
        if (authors.length > 0) {
            log.error("Author list should be empty");
            throw new AssertionError("Authors list should be empty");
        }
    }

    @Step("Verify author has valid ID")
    @Override
    public void verifyHasValidID(Author author) {
        if (author.getId() == null) {
            log.error("Author ID should not be null");
            throw new AssertionError("Author ID should not be null");
        }
    }


    //Author Specific

    @Step("Verify author ID matches expected: {expectedId}")
    public void verifyAuthorID(Author author, int expectedId) {
        if (author.getId() == null) {
            log.error("Author ID should not be null");
            throw new AssertionError("Author ID should not be null");
        }
        if (!author.getId().equals(expectedId)) {
            log.error("Author ID expected to be: {} but was: {}", expectedId, author.getId());
            throw new AssertionError(
                    String.format("Expected author ID %d but got %d", expectedId, author.getId())
            );
        }
    }

    @Step("Verify author ID Book matches expected: {expectedIdBook}")
    public void verifyAuthorIDBook(Author author, int expectedIdBook) {
        if (author.getIdBook() == null) {
            log.error("Author ID Book should not be null");
            throw new AssertionError("Author ID Book should not be null");
        }
        if (!author.getIdBook().equals(expectedIdBook)) {
            log.error("Author ID Book expected to be: {} but was: {}", expectedIdBook, author.getIdBook());
            throw new AssertionError(
                    String.format("Expected author ID Book %d but got %d", expectedIdBook, author.getIdBook())
            );
        }
    }

    @Step("Verify author has valid ID Book")
    public void verifyAuthorHasValidIDBook(Author author) {
        if (author.getIdBook() == null) {
            log.error("Author ID Book should not be null");
            throw new AssertionError("Author ID Book should not be null");
        }
    }

    @Step("Verify author first name matches expected: {expectedFirstName}")
    public void verifyAuthorFirstName(Author author, String expectedFirstName) {
        if (author.getFirstName() == null) {
            log.error("Author first name should not be null");
            throw new AssertionError("Author first name should not be null");
        }
        if (!author.getFirstName().equals(expectedFirstName)) {
            log.error("Author first name expected to be: {} but was: {}", expectedFirstName, author.getFirstName());
            throw new AssertionError(
                    String.format("Expected first name '%s' but got '%s'",
                            expectedFirstName, author.getFirstName())
            );
        }
    }

    @Step("Verify author last name matches expected: {expectedLastName}")
    public void verifyAuthorLastName(Author author, String expectedLastName) {
        if (author.getLastName() == null) {
            log.error("Author last name should not be null");
            throw new AssertionError("Author last name should not be null");
        }
        if (!author.getLastName().equals(expectedLastName)) {
            log.error("Author last name expected to be: {} but was: {}", expectedLastName, author.getLastName());
            throw new AssertionError(
                    String.format("Expected last name '%s' but got '%s'",
                            expectedLastName, author.getLastName())
            );
        }
    }

    @Step("Verify author full name matches expected: {expectedFullName}")
    public void verifyAuthorFullName(Author author, String expectedFullName) {
        if (author.getFirstName() == null || author.getLastName() == null) {
            log.error("Author first name or last name should not be null");
            throw new AssertionError("Author first name and last name should not be null");
        }
        String fullName = author.getFirstName() + " " + author.getLastName();
        if (!fullName.equals(expectedFullName)) {
            log.error("Author full name expected to be: {} but was: {}", expectedFullName, fullName);
            throw new AssertionError(
                    String.format("Expected full name '%s' but got '%s'",
                            expectedFullName, fullName)
            );
        }
    }
}
