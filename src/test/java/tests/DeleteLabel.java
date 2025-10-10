package tests;

import base.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DeleteLabel extends BaseClass
{
    @Test
    public void deleteLabel_DELETE ()
    {
        getTest().info("DELETE request - To delete a label from the board.");

        logger.info("Sending DELETE request to delete a label.");

        Response response = given()
                                .spec(reqSpec)
                                .basePath("/1/labels/{labelId}")
                                .pathParam("labelId", ConfigReader.get("labelId"))
                            .when()
                                .delete();

        logger.info("Response status code : {}", response.getStatusCode());
        logger.info("Response body : {}", response.asPrettyString());

        getTest().info("Response:\n" + response.asPrettyString());

        try{
            assertEquals(response.getStatusCode(),200,"Status code should be 200");
            getTest().pass("Deleted a Label form the Board successfully with Status Code 200");
        }
        catch (AssertionError e)
        {
            getTest().fail("Assertion failed: " + e.getMessage());
            throw e;
        }

    }
}
