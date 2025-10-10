package tests;

import base.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class GetBoardDetails extends BaseClass
{
    @Test
    public void getBoardDetails_GET ()
    {
        getTest().info("GET request - To fetch board details");

        logger.info("Sending GET request to fetch details");

        Response response = given()
                                .spec(reqSpec)
                                .basePath("/1/boards/{id}/lists")
                                .pathParam("id",ConfigReader.get("boardId"))
                            .when()
                                .get();

        logger.info("Response status code : {}", response.getStatusCode());
        logger.info("Response body : {}", response.asPrettyString());
        getTest().info("Response:\n" + response.asPrettyString());

        try{
            assertEquals(response.getStatusCode(),200,"Status code should be 200");
            getTest().pass("Retrieved Board details successfully with Status Code 200");
        }
        catch (AssertionError e)
        {
            getTest().fail("Assertion failed: " + e.getMessage());
            throw e;
        }

    }

}


