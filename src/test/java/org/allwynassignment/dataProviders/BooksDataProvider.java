package org.allwynassignment.dataProviders;

import org.testng.annotations.DataProvider;

public class BooksDataProvider {
    @DataProvider(name = "validBookIds")
    public static Object[][] validBookIds() {
        return new Object[][] {
                {1},
                {2},
                {5},
                {10}
        };
    }

    @DataProvider(name = "invalidBookIds")
    public static Object[][] invalidBookIds() {
        return new Object[][] {
                {-1},
                {0},
                {999999999}
        };
    }

    @DataProvider(name = "mixedBookIds")
    public static Object[][] mixedBookIds() {
        return new Object[][] {
                {1, true},
                {-5, false},
                {1000, false}
        };
    }
}
