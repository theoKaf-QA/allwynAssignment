package org.allwynassignment.APITestAutomation.utils;

import org.allwynassignment.APITestAutomation.models.Author;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuthorDataFactory {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static Author createValidAuthor() {
        return Author.builder()
                .id(9999)
                .idBook(1)
                .firstName("Test" + System.currentTimeMillis())
                .lastName("Writer")
                .build();
    }

    public static Author createInvalidAuthor() {
        return Author.builder()
                .id(-1)
                .idBook(-1)
                .firstName("")
                .lastName("")
                .build();
    }

    private static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }
}
