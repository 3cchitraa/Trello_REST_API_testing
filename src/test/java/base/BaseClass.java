package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseClass
{
    private static ExtentReports extentReport;
    private static ExtentSparkReporter spark;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected Logger logger;
    protected RequestSpecification reqSpec;

    @BeforeSuite
    public void initExtentReport()
    {
        if(extentReport==null) {
            spark = new ExtentSparkReporter("./test-output/ExtentReport.html");
            spark.config().setDocumentTitle("API Automation Report");
            spark.config().setReportName("Execution Summary");
            spark.config().setTheme(Theme.STANDARD);

            extentReport = new ExtentReports();
            extentReport.attachReporter(spark);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setupClassLevel(ITestContext context)
    {
        // Dynamically use current class name
        logger = LogManager.getLogger(getClass());
        logger.info("Logger initialized for: " + getClass().getSimpleName());

        logger.info("Setting up Request Specifications");

        reqSpec = new RequestSpecBuilder()
                                        .setBaseUri(ConfigReader.get("baseUri"))
                                        .addHeader("Content-Type",ConfigReader.get("contentType"))
                                        .addHeader("Accept",ConfigReader.get("acceptType"))
                                        .addQueryParam("key",ConfigReader.get("apiKey"))
                                        .addQueryParam("token",ConfigReader.get("apiToken"))
                                        .build();

        logger.info("RequestSpecification done");
    }

    @BeforeMethod(alwaysRun = true)
    public void setExtentReport(Method method)
    {
        String testName = method.getName();
        ExtentTest extentTest = extentReport.createTest(testName + " - " + getClass().getSimpleName());
        test.set(extentTest);
        logger.info("ExtentTest started for method: " + testName);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown()
    {
        if(extentReport!=null)
        {
            extentReport.flush();
            logger.info("Extent report flushed successfully.");
        }
        logger.info("Suite level Execution Done");
    }

    public ExtentTest getTest()
    {
        return test.get();
    }

    public void validateErrorResponse(Response response, int expectedStatusCode, String expectedErrorSubstring) {
        try {
            assertEquals(response.getStatusCode(), expectedStatusCode,
                    "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());

            assertTrue(response.asString().toLowerCase().contains(expectedErrorSubstring.toLowerCase()),
                    "Expected response body to contain: \"" + expectedErrorSubstring + "\" but got:\n" + response.asString());

            getTest().pass("Negative scenario validated successfully. Status code: " + response.getStatusCode());

        } catch (AssertionError e) {
            getTest().fail("Validation failed: " + e.getMessage());
            logger.error("Negative scenario validation failed", e);
            throw e;
        }
    }


}
