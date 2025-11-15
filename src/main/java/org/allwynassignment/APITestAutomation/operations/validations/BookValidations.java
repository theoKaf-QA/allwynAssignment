package org.allwynassignment.APITestAutomation.operations.validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.models.Book;

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
        if (book == null) {
            log.error("Book should not be null");
            throw new AssertionError("Book should not be null");
        }
    }

    @Step("Verify book is null")
    @Override
    public void verifyIsNull(Book book) {
        if (book != null) {
            log.error("Book should be null");
            throw new AssertionError("Book should be null");
        }
    }

    @Step("Verify books list is not empty")
    @Override
    public void verifyListNotEmpty(Book[] books) {
        if (books == null) {
            log.error("Books list should not be null");
            throw new AssertionError("Books list should not be null");
        }
        if (books.length == 0) {
            log.error("Books list should not be empty");
            throw new AssertionError("Books list should not be empty");
        }
    }

    @Step("Verify books list is empty")
    @Override
    public void verifyListIsEmpty(Book[] books) {
        if (books == null) {
            log.error("Books list should not be null");
            throw new AssertionError("Books list should not be null");
        }
        if (books.length == 0) {
            log.error("Books list should be empty");
            throw new AssertionError("Books list should be empty");
        }
    }

    @Step("Verify book has valid ID")
    @Override
    public void verifyHasValidID(Book book) {
        if (book.getId() == null) {
            log.error("Book ID should not be null");
            throw new AssertionError("Book ID should not be null");
        }
    }

    // Book Specific

    @Step("Verify book ID matches expected: {expectedId}")
    public void verifyBookId(Book book, int expectedId) {
        if (book.getId() == null) {
            log.error("Book ID should not be null");
            throw new AssertionError("Book ID should not be null");
        }
        if (!book.getId().equals(expectedId)) {
            log.error("Book ID expected to be {} but was {}", expectedId, book.getId());
            throw new AssertionError(
                    String.format("Expected book ID %d but got %d", expectedId, book.getId())
            );
        }
    }

    @Step("Verify book title matches expected: {expectedTitle}")
    public void verifyBookTitle(Book book, String expectedTitle) {
        if (book.getTitle() == null) {
            log.error("Book title should not be null");
            throw new AssertionError("Book title should not be null");
        }
        if (!book.getTitle().equals(expectedTitle)) {
            log.error("Book title expected to be {} but was {}", expectedTitle, book.getTitle());
            throw new AssertionError(
                    String.format("Expected title '%s' but got '%s'", expectedTitle, book.getTitle())
            );
        }
    }

    @Step("Verify book has valid title")
    public void verifyBookHasValidTitle(Book book) {
        if (book.getTitle() == null) {
            log.error("Book title should not be null");
            throw new AssertionError("Book title should not be null");
        }
    }

    @Step("Verify book description matches expected: {expectedDescription}")
    public void verifyBookDescription(Book book, String expectedDescription) {
        if (book.getDescription() == null) {
            log.error("Book description should not be null");
            throw new AssertionError("Book description should not be null");
        }
        if (!book.getDescription().equals(expectedDescription)) {
            log.error("Book description expected to be {} but was {}", expectedDescription, book.getDescription());
            throw new AssertionError(
                    String.format("Expected description '%s' but got '%s'",
                            expectedDescription, book.getDescription())
            );
        }
    }

    @Step("Verify book has valid description")
    public void verifyBookHasValidDescription(Book book) {
        if (book.getDescription() == null) {
            log.error("Book description should not be null");
            throw new AssertionError("Book description should not be null");
        }
    }

    @Step("Verify book page count matches expected: {expectedPageCount}")
    public void verifyBookPageCount(Book book, int expectedPageCount) {
        if (book.getPageCount() == null) {
            log.error("Book page count should not be null");
            throw new AssertionError("Book page count should not be null");
        }
        if (!book.getPageCount().equals(expectedPageCount)) {
            log.error("Book page count expected to be {} but was {}", expectedPageCount, book.getPageCount());
            throw new AssertionError(
                    String.format("Expected page count %d but got %d",
                            expectedPageCount, book.getPageCount())
            );
        }
    }

    @Step("Verify book has valid page count")
    public void verifyBookHasValidPageCount(Book book) {
        if (book.getPageCount() == null) {
            log.error("Book page count should not be null");
            throw new AssertionError("Book page count should not be null");
        }
    }

    @Step("Verify book excerpt matches expected: {expectedExcerpt}")
    public void verifyBookExcerpt(Book book, String expectedExcerpt) {
        if (book.getExcerpt() == null) {
            log.error("Book excerpt should not be null");
            throw new AssertionError("Book excerpt should not be null");
        }
        if (!book.getExcerpt().equals(expectedExcerpt)) {
            log.error("Book excerpt expected to be {} but was {}", expectedExcerpt, book.getExcerpt());
            throw new AssertionError(
                    String.format("Expected excerpt '%s' but got '%s'",
                            expectedExcerpt, book.getExcerpt())
            );
        }
    }

    @Step("Verify book has valid excerpt")
    public void verifyBookHasValidExcerpt(Book book) {
        if (book.getExcerpt() == null) {
            log.error("Book excerpt should not be null");
            throw new AssertionError("Book excerpt should not be null");
        }
    }

    @Step("Verify book publish date matches expected: {expectedPublishDate}")
    public void verifyBookPublishDate(Book book, String expectedPublishDate) {
        if (book.getPublishDate() == null) {
            log.error("Book publish date should not be null");
            throw new AssertionError("Book publish date should not be null");
        }
        if (!book.getPublishDate().equals(expectedPublishDate)) {
            log.error("Book publish date expected to  be {} but was {}", expectedPublishDate, book.getPublishDate());
            throw new AssertionError(
                    String.format("Expected publish date '%s' but got '%s'",
                            expectedPublishDate, book.getPublishDate())
            );
        }
    }
    @Step("Verify book has valid publish date")
    public void verifyBookHasValidPublishDate(Book book) {
        if (book.getPublishDate() == null) {
            log.error("Book publish date should not be null");
            throw new AssertionError("Book publish date should not be null");
        }
    }

    @Step("Verify book publish date format is valid (ISO 8601)")
    public void verifyBookPublishDateFormat(Book book) {
        if (book.getPublishDate() == null) {
            log.error("Book publish date should not be null");
            throw new AssertionError("Book publish date should not be null");
        }
        // Basic ISO 8601 format check: yyyy-MM-ddTHH:mm:ss.SSSZ
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{3})?Z?$";
        if (!book.getPublishDate().matches(datePattern)) {
            log.error("Book publish date format is not valid, expected format is {} but got {}", datePattern, book.getPublishDate());
            throw new AssertionError(
                    String.format("Expected publish date in ISO 8601 format but got '%s'",
                            book.getPublishDate())
            );
        }
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
