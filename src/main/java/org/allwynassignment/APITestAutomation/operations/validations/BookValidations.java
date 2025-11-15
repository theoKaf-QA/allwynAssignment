package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.models.Book;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BookValidations extends ValidationsAbstract<Book> implements ValidationInterface<Book> {

    //Interface inherited methods

    @Step("Parse single book from response")
    @Override
    public Book parseResponse(Response response) {
        return response.as(Book.class);
    }

    @Step("Parse books list from response")
    @Override
    public Book[] parseListResponse(Response response) {
        return response.as(Book[].class);
    }

    @Step("Verify book is not null")
    @Override
    public void verifyNotNull(Book book) {
        assertThat(book)
                .as("Book object is null")
                .isNotNull();
    }

    @Step("Verify book is null")
    @Override
    public void verifyIsNull(Book book) {
        assertThat(book)
                .as("Book object is null")
                .isNull();
    }

    @Step("Verify books list is not empty")
    @Override
    public void verifyListNotEmpty(Book[] books) {
        assertThat(books)
                .as("Books list object is null")
                .isNotNull()
                .isNotEmpty();
    }

    @Step("Verify books list is empty")
    @Override
    public void verifyListIsEmpty(Book[] books) {
        assertThat(books)
                .as("Books list object is null")
                .isNotNull()
                .isEmpty();
    }

    @Step("Verify book has valid ID")
    @Override
    public void verifyHasValidID(Book book) {
        assertThat(book.getId())
                .as("Book id is null")
                .isNotNull();
    }

    // Book Specific

    @Step("Verify book ID matches expected: {expectedId}")
    public void verifyBookId(Book book, int expectedId) {
        assertThat(book.getId())
                .as("Book id is expected: {}", expectedId)
                .isEqualTo(expectedId);
    }

    @Step("Verify book title matches expected: {expectedTitle}")
    public void verifyBookTitle(Book book, String expectedTitle) {
        assertThat(book.getTitle())
                .as("Book title is expected: {}", expectedTitle)
                .isNotNull()
                .matches(expectedTitle);
    }

    @Step("Verify book has valid title")
    public void verifyBookHasValidTitle(Book book) {
        assertThat(book.getTitle())
                .as("Book title is null")
                .isNotNull();
    }

    @Step("Verify book description matches expected: {expectedDescription}")
    public void verifyBookDescription(Book book, String expectedDescription) {
        assertThat(book.getDescription())
                .as("Book description is expected: {}", expectedDescription)
                .isNotNull()
                .matches(expectedDescription);
    }

    @Step("Verify book has valid description")
    public void verifyBookHasValidDescription(Book book) {
        assertThat(book.getTitle())
                .as("Book description is null")
                .isNotNull();
    }

    @Step("Verify book page count matches expected: {expectedPageCount}")
    public void verifyBookPageCount(Book book, int expectedPageCount) {
        assertThat(book.getPageCount())
                .as("Book page count is expected: {}", expectedPageCount)
                .isNotNull()
                .isEqualTo(expectedPageCount);
    }

    @Step("Verify book has valid page count")
    public void verifyBookHasValidPageCount(Book book) {
        assertThat(book.getPageCount())
                .as("Book page count is null")
                .isNotNull();
    }

    @Step("Verify book excerpt matches expected: {expectedExcerpt}")
    public void verifyBookExcerpt(Book book, String expectedExcerpt) {
        assertThat(book.getExcerpt())
                .as("Book excerpt is expected: {}", expectedExcerpt)
                .isNotNull()
                .matches(expectedExcerpt);
    }

    @Step("Verify book has valid excerpt")
    public void verifyBookHasValidExcerpt(Book book) {
        assertThat(book.getPageCount())
                .as("Book excerpt is null")
                .isNotNull();
    }

    @Step("Verify book publish date matches expected: {expectedPublishDate}")
    public void verifyBookPublishDate(Book book, String expectedPublishDate) {
        assertThat(book.getPublishDate())
                .as("Book publish date is expected: {}", expectedPublishDate)
                .isNotNull()
                .matches(expectedPublishDate);
    }
    @Step("Verify book has valid publish date")
    public void verifyBookHasValidPublishDate(Book book) {
        assertThat(book.getPublishDate())
                .as("Book publish date is null")
                .isNotNull();
    }

    @Step("Verify book publish date format is valid (ISO 8601)")
    public void verifyBookPublishDateFormat(Book book) {
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?Z?$";
        assertThat(book.getPublishDate())
                .as("Book publish date should be in ISO 8601 format")
                .isNotNull()
                .matches(datePattern);
    }


    @Step("Verify book has all required fields")
    public void verifyBookHasAllRequiredFields(Book book) {
        verifyNotNull(book);
        verifyHasValidID(book);
        verifyBookHasValidTitle(book);
        verifyBookHasValidDescription(book);
        verifyBookHasValidPageCount(book);
        verifyBookHasValidExcerpt(book);
        verifyBookHasValidPublishDate(book);
    }

}
