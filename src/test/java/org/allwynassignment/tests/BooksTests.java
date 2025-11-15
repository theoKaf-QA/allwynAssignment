package org.allwynassignment.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.models.Book;
import org.allwynassignment.APITestAutomation.utils.BookDataFactory;
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
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
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
            groups = {"smoke", "regression"},
            dataProvider = "validBookIds", dataProviderClass = BooksDataProvider.class)
    @Tags({@Tag("smoke"), @Tag("regression")})
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


    @Test(priority = 4, description = "Verify POST creates a new book successfully",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that POST /Books endpoint creates a new author")
    public void testCreateAuthor_ValidData_Success() {
        Book validBook = BookDataFactory.createValidBook();

        Response response = bookActions.addBook(validBook);

        bookValidations.verifyStatusCode(response, 200);

        Book bookResponse = bookValidations.parseResponse(response);
        bookValidations.verifyBookHasAllRequiredFields(bookResponse);
        bookValidations.verifyNotNull(bookResponse);
        bookValidations.verifyBookId(bookResponse, validBook.getId());
        bookValidations.verifyBookHasValidTitle(bookResponse);
        bookValidations.verifyBookDescription(bookResponse, validBook.getDescription());
    }

    @Test(priority = 5, description = "Verify PUT updates an existing book successfully",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that PUT /Books/{id} endpoint updates an existing book")
    public void testUpdateBook_ValidData_Success() {
        Book updatedBook = BookDataFactory.updateValidBook(BookDataFactory.createValidBook());
        int bookId = updatedBook.getId();

        Response response = bookActions.updateBook(bookId, updatedBook);

        bookValidations.verifyStatusCode(response, 200);

        Book book = bookValidations.parseResponse(response);
        bookValidations.verifyNotNull(book);
        bookValidations.verifyBookId(book, bookId);
        bookValidations.verifyBookDescription(book, "This is a test book");
    }

    @Test(priority = 6, description = "Verify DELETE removes a book successfully",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that DELETE /Books/{id} endpoint deletes a book")
    public void testDeleteBook_ValidId_Success() {
        int bookId = 1;
        Response response = bookActions.deleteBook(bookId);
        bookValidations.verifyStatusCode(response, 200);
    }



    @Test(priority = 7, description = "Verify GET book with nonexistent ID returns 404",
            groups = {"smoke", "regression"})
    @Tags({@Tag("smoke"), @Tag("regression")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that GET /Books/{id} returns 404 for nonexistent book ID")
    public void testGetBookById_NonExistentId_ReturnsNotFound() {
        int bookId = 999999;
        Response response = bookActions.getBookById(bookId);
        bookValidations.verifyStatusCode(response, 404);
    }


    @Test(priority = 8, description = "Verify GET book with zero ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when requesting book with ID = 0")
    public void testGetBookById_ZeroId() {
        int bookId = 0;
        Response response = bookActions.getBookById(bookId);
        bookValidations.verifyErrorStatusCode(response);
    }

    @Test(priority = 9, description = "Verify GET book with negative ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when requesting book with negative ID")
    public void testGetBookById_NegativeId() {
        int bookId = -1;
        Response response = bookActions.getBookById(bookId);
        bookValidations.verifyErrorStatusCode(response);
    }

    @Test(priority = 10, description = "Verify POST book with the least amount of data",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that POST /Books works with the least amount of fields")
    public void testCreateBook_OnlyID_Success() {
        Book minimalBook = BookDataFactory.createMinimalBook();

        Response response = bookActions.addBook(minimalBook);
        Book responseBook = bookValidations.parseResponse(response);

        bookValidations.verifyBookHasInvalidTitle(responseBook);
        bookValidations.verifyBookHasInvalidDescription(responseBook);
        bookValidations.verifyBookPageCount(responseBook,0);
        bookValidations.verifyBookHasInvalidExcerpt(responseBook);
        bookValidations.verifyBookPublishDate(responseBook,"0001-01-01T00:00:00");
    }

    @Test(priority = 11, description = "Verify POST book with empty body",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that POST /Books works with an empty body")
    public void testCreateBook_NoFields_Success() {
        Book emptyBook = BookDataFactory.createEmptyBook();

        Response response = bookActions.addBook(emptyBook);
        Book responseBook = bookValidations.parseResponse(response);

        bookValidations.verifyBookId(responseBook,0);
        bookValidations.verifyBookHasInvalidTitle(responseBook);
        bookValidations.verifyBookHasInvalidDescription(responseBook);
        bookValidations.verifyBookPageCount(responseBook,0);
        bookValidations.verifyBookHasInvalidExcerpt(responseBook);
        bookValidations.verifyBookPublishDate(responseBook,"0001-01-01T00:00:00");
    }

    @Test(priority = 12, description = "Verify POST book with invalid data types",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify error handling with invalid book fields")
    public void testCreateBook_InvalidData() {
        Book invalidBook = BookDataFactory.createInvalidBook();
        Response response = bookActions.addBook(invalidBook);
        bookValidations.verifyErrorStatusCode(response);
    }

    @Test(priority = 13, description = "Verify PUT book with negative ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when updating negative ID book")
    public void testUpdateBook_NegativeId_Success() {
        int bookId  = -1;
        Book book = BookDataFactory.createValidBook();

        Response response = bookActions.updateBook(bookId, book);

        bookValidations.verifySuccessfulStatusCode200(response);
    }

    @Test(priority = 14, description = "Verify DELETE book with negative ID",
            groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify behavior when deleting negative ID book")
    public void testDeleteBook_NegativeId_Success() {
        int bookId  = -1;
        Book book = BookDataFactory.createValidBook();

        Response response = bookActions.updateBook(bookId, book);

        bookValidations.verifySuccessfulStatusCode200(response);
    }

    @Test(priority = 15, description = "Verify large page count is handled correctly")
    @Story("Create Book")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to verify handling of extreme values for pageCount")
    public void testCreateBook_LargePageCount() {
        Book book = BookDataFactory.createCustomBook(
                Book.builder()
                        .id(900)
                        .title("Large page count")
                        .description("Large page count")
                        .pageCount(Integer.MAX_VALUE)
                        .excerpt("Large page count")
                        .build()
        );

        Response response = bookActions.addBook(book);

        bookValidations.verifyStatusCode(response, 200);
    }


    @Test(priority = 20, description = "Verify GET book with invalid IDs, fails",
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
