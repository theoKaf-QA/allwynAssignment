package org.allwynassignment.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import io.restassured.response.Response;
import org.allwynassignment.APITestAutomation.models.Author;
import org.testng.annotations.Test;

@Epic("Allwyn Assignment Test Automation")
@Owner("Theodoros Kafazis")
@Feature("Authors FakeRestAPI Tests")
public class AuthorsTests extends BaseTest {

    @Test(priority = 1, description = "Verify GET all authors returns 200 and list of authors",
    groups = {"smoke"})
    @Tags({@Tag("smoke")})
    @Story("Get All Authors")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that GET /Authors endpoint returns successful response")
    public void testGetAllAuthors_Success() {
        Response response = authorActions.getAllAuthors();

        authorValidations.verifyStatusCode(response, 200);

        Author[] authors = authorValidations.parseListResponse(response);
        authorValidations.verifyListNotEmpty(authors);

        Author firstAuthor = authors[0];
        authorValidations.verifyHasValidID(firstAuthor);
    }
}
