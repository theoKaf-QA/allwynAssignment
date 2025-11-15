package org.allwynassignment.APITestAutomation.utils;

import lombok.Builder;
import org.allwynassignment.APITestAutomation.models.Book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookDataFactory {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static Book createValidBook() {
        return Book.builder()
                .id(9999)
                .title("TestBook: " + System.currentTimeMillis())
                .description("This is a test book")
                .pageCount(152)
                .excerpt("Test excrept")
                .publishDate(getCurrentDateTime())
                .build();
    }

    public static Book createMinimalBook() {
        return Book.builder()
                .id(22)
                .build();
    }

    public static Book createEmptyBook() {
        return Book.builder()
                .build();
    }

    public static Book updateValidBook(Book book) {
        return book.toBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .pageCount(book.getPageCount())
                .excerpt(book.getExcerpt())
                .publishDate(book.getPublishDate())
                .build();
    }

    public static Book createInvalidBook() {
        return Book.builder()
                .id(-1)
                .title("")
                .description("")
                .pageCount(-100)
                .excerpt("")
                .publishDate("random-invalid-date")
                .build();
    }

    public static Book createCustomBook(Book book) {
        return Book.builder()
                .id(9999)
                .title("TestBook: " + System.currentTimeMillis())
                .description("This is a test book")
                .pageCount(152)
                .excerpt("Test excrept")
                .publishDate(getCurrentDateTime())
                .build();
    }

    private static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

}
