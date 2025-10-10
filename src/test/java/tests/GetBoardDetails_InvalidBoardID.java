package tests;

import base.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GetBoardDetails_InvalidBoardID extends BaseClass
{
    @Test
    public void getBoardWithInvalidId() {
        getTest().info("GET request - To fetch board details via INVALID Board ID.");

        logger.info("Sending GET request to fetch details via INVALID Board ID.");

        Response response = given()
                .spec(reqSpec)
                .basePath("/1/boards/{id}/lists")
                .pathParam("id", ConfigReader.get("invalidBoardId"))
                .when()
                .get();

        logger.info("Response status code for an Invalid input : {}", response.getStatusCode());
        logger.info("Negative Test - Invalid boardId response: " + response.asPrettyString());
        getTest().info("Response:\n" + response.asPrettyString());

//       GetBoardDetails_InvalidBoardID

        validateErrorResponse(response, 404, ConfigReader.get("expectedErrorMsg"));

    }

}
