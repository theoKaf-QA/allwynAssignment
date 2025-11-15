package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.models.Author;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(author)
                .as("Author object is null")
                .isNotNull();
    }

    @Step("Verify author is null")
    @Override
    public void verifyIsNull(Author author) {
        assertThat(author)
                .as("Author object is null")
                .isNull();
    }

    @Step("Verify authors list is not empty")
    @Override
    public void verifyListNotEmpty(Author[] authors) {
        assertThat(authors)
                .as("Authors list empty")
                .isNotNull()
                .isNotEmpty();
    }

    @Step("Verify authors list is empty")
    @Override
    public void verifyListIsEmpty(Author[] authors) {
        assertThat(authors)
                .as("Authors list empty")
                .isNotNull()
                .isEmpty();
    }

    @Step("Verify author has valid ID")
    @Override
    public void verifyHasValidID(Author author) {
        assertThat(author.getId())
                .as("Author id is null")
                .isNotNull();
    }


    //Author Specific

    @Step("Verify author ID matches expected: {expectedId}")
    public void verifyAuthorID(Author author, int expectedId) {
        assertThat(author.getId())
                .as("Author id is expected: {}", expectedId)
                .isEqualTo(expectedId);
    }

    @Step("Verify author ID Book matches expected: {expectedIdBook}")
    public void verifyAuthorIDBook(Author author, int expectedIdBook) {
        assertThat(author.getIdBook())
                .as("Author idBook is expected: {}", expectedIdBook)
                .isEqualTo(expectedIdBook);
    }

    @Step("Verify author has valid ID Book")
    public void verifyAuthorHasValidIDBook(Author author) {
        assertThat(author.getIdBook())
                .as("Author idBook is expected: {}", author.getIdBook())
                .isNotNull();
    }

    @Step("Verify author first name matches expected: {expectedFirstName}")
    public void verifyAuthorFirstName(Author author, String expectedFirstName) {
        assertThat(author.getFirstName())
                .as("Author firstname is expected:  {}", expectedFirstName)
                .matches(expectedFirstName);
    }

    @Step("Verify author last name matches expected: {expectedLastName}")
    public void verifyAuthorLastName(Author author, String expectedLastName) {
        assertThat(author.getLastName())
                .as("Author lastname is expected:  {}", expectedLastName)
                .matches(expectedLastName);
    }

    @Step("Verify author full name matches expected: {expectedFullName}")
    public void verifyAuthorFullName(Author author, String expectedFullName) {
        String actualFullName = author.getFirstName() + " " + author.getLastName();
        assertThat(actualFullName)
                .as("Author full name is expected:   {}", expectedFullName)
                .isEqualTo(expectedFullName);
    }


    @Step("Verify author has all required fields")
    public void verifyAuthorHasAllRequiredFields(Author author) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(author)
                .as("Author object")
                .isNotNull();

        softly.assertThat(author.getId())
                .as("Author ID")
                .isNotNull();

        softly.assertThat(author.getIdBook())
                .as("Author ID Book")
                .isNotNull();

        softly.assertThat(author.getFirstName())
                .as("Author first name")
                .isNotNull();

        softly.assertThat(author.getLastName())
                .as("Author last name")
                .isNotNull();

        softly.assertAll();
    }
}
