package org.allwynassignment.tests;


import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.models.Book;
import org.allwynassignment.dataProviders.BooksDataProvider;
import org.testng.annotations.Test;

@Epic("Allwyn Assignment Test Automation")
@Owner("Theodoros Kafazis")
@Feature("Books FakeRestAPI Tests")
public class BooksTests extends BaseTest{

    @Test(priority = 1, description = "Verify GET books returns 200 and list of books",
          groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /Books endpoint returns successful response with a list of books")
    public void testGetAllBooks_Success() {
        Response response = bookActions.getAllBooks();
        bookValidations.verifyStatusCode(response, 200);

        Book[] books = bookValidations.parseListResponse(response);
        bookValidations.verifyListNotEmpty(books);

        Book firstBook = books[0];
        bookValidations.verifyHasValidID(firstBook);
        bookValidations.verifyBookHasValidTitle(firstBook);
    }


    @Test(priority = 2, description = "Verify GET book by valid ID returns 200 and book details",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /Books/{id} endpoint finds the book with the specified ID and validates its details")
    public void testGetBookById_ValidId_Success() {
        int validBookId = 1;
        Response response = bookActions.getBookById(validBookId);

        bookValidations.verifyStatusCode(response, 200);

        Book book = bookValidations.parseResponse(response);
        bookValidations.verifyNotNull(book);
        bookValidations.verifyBookId(book, validBookId);
        bookValidations.verifyBookHasValidTitle(book);
    }


    @Test(priority = 3, description = "Verify GET book with multiple valid IDs, they all return 200 and book details",
            groups = {"smoke"},
            dataProvider = "validBookIds", dataProviderClass = BooksDataProvider.class)
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /Books/{id} endpoint finds the book with the specified ID and validates its details")
    public void testGetBookById_ValidId_Success(int validBookId) {
        Response response = bookActions.getBookById(validBookId);

        bookValidations.verifyStatusCode(response, 200);

        Book book = bookValidations.parseResponse(response);
        bookValidations.verifyNotNull(book);
        bookValidations.verifyBookId(book, validBookId);
        bookValidations.verifyBookHasValidTitle(book);
    }

    @Test(priority = 4, description = "Verify GET book with invalid IDs, must fail",
            groups = {"smoke"},
            dataProvider = "invalidBookIds", dataProviderClass = BooksDataProvider.class)
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that GET /Books/{id} endpoint finds the book with the specified ID and validates its details")
    public void testGetBookById_InvalidId_Fail(int invalidBookId) {
        Response response = bookActions.getBookById(invalidBookId);

        bookValidations.verifyStatusCode(response, 200);

        Book book = bookValidations.parseResponse(response);
        bookValidations.verifyNotNull(book);
        bookValidations.verifyBookId(book, invalidBookId);
        bookValidations.verifyBookHasValidTitle(book);
    }
}
