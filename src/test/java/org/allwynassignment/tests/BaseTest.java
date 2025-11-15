package org.allwynassignment.tests;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.allwynassignment.APITestAutomation.operations.actions.AuthorActions;
import org.allwynassignment.APITestAutomation.operations.actions.BookActions;
import org.allwynassignment.APITestAutomation.operations.validations.AuthorValidations;
import org.allwynassignment.APITestAutomation.operations.validations.BookValidations;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public abstract class BaseTest {
    protected BookActions bookActions;
    protected AuthorActions authorActions;
    protected BookValidations bookValidations;
    protected AuthorValidations authorValidations;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        bookActions = new BookActions();
        authorActions = new AuthorActions();
        bookValidations = new BookValidations();
        authorValidations = new AuthorValidations();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("Failure Reason", result.getThrowable().getMessage());
        }
    }

    protected void assertTrue(boolean condition, String message) {
        if (!condition) {
            log.error("Expected condition: {} to be true but it was false. Message:", message);
            throw new AssertionError(message);
        }
    }
    protected void assertFalse(boolean condition, String message) {
        if (condition) {
            log.error("Expected condition: {} to be false but it was true. Message:", message);
            throw new AssertionError(message);
        }
    }

}
