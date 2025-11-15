package org.allwynassignment.APITestAutomation.utils;

import org.allwynassignment.APITestAutomation.models.Book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookDataFactory {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");


    //Just for show, in case we have access to a DB to run those tests

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

    private static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

}
