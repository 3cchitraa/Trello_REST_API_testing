package tests;

import base.BaseClass;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.PayloadManager;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class PUT_UpdateBoardName extends BaseClass
{
    @Test
    public void updateBoardName_PUT()
    {
        getTest().info("PUT request - To Update the board name.");

        String expectedBoardName = PayloadManager.getBoardName("updateBoardName.json");

        logger.info("Starting Update Board Name Test");

        //Read payload from .json file
        String payload = PayloadManager.getPayload("updateBoardName.json");
        logger.info("Request payload: {}", payload);

        Response response = given()
                                .spec(reqSpec)
                                .basePath("/1/boards/{id}")
                                .pathParam("id",ConfigReader.get("boardId"))
                                .body(payload)
                            .when()
                                .put()
                            .then()
                                    .log().all()
                                    .assertThat()
                                    .statusCode(200)
                                    .body("name", equalTo(expectedBoardName))
                                    .extract().response();

        logger.info("Response status code : {}", response.getStatusCode());
        logger.info("Response body : {}", response.asPrettyString());

        try {
            Assert.assertEquals(200, response.getStatusCode(), "Status code should be 200");
            getTest().pass("Board name updated successfully.");
        } catch (AssertionError e) {
            getTest().fail("Assertion failed: " + e.getMessage());
            throw e;
        }
    }

}
