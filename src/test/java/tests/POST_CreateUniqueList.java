package tests;

import base.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.DataGenerator;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;


public class POST_CreateUniqueList extends BaseClass
{
    @Test
    public void createNewList_POST()
    {
        String uniqueList = DataGenerator.generateUniqueName("ListByChitra");

        logger.info("Generated Unique list" + uniqueList);

        getTest().info("POST request - Creating Unique List name under the board"+ uniqueList);

        logger.info("Sending POST request to Create a Unique List under the Board.");

        Response response = given()
                                .spec(reqSpec)
                                .basePath("/1/boards/{id}/lists/")
                                .pathParam("id",ConfigReader.get("boardId"))
                                .queryParam("name",uniqueList)
                            .when()
                                .post();

        logger.info("Response status code : {}", response.getStatusCode());
        logger.info("Response body : {}", response.body().prettyPrint());

        getTest().info("Response:\n" + response.body().prettyPrint().toString());

        try{
            assertEquals(response.getStatusCode(),200,"Status code should be 200");
            getTest().pass("Created Unique List successfully with Status Code 200");
        }
        catch (AssertionError e)
        {
            getTest().fail("Assertion failed: " + e.getMessage());
            throw e;
        }

    }
}
