package org.ESPN.tests;

import org.ESPN.configuration.Driver;
import org.ESPN.pages.HomePage;
import org.ESPN.reporting.Reporter;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static java.lang.String.format;

public class BaseTest {
    protected HomePage home;
    private Driver driver;

    @Parameters({"browser", "url"})
    @BeforeClass()
    public void testSetup(String browser, String url) {
        driver = new Driver(browser);
        Reporter.info("Deleting all the cookies");
        driver.getDriver().manage().deleteAllCookies();
        Reporter.info(format("Navigating to %s", url));
        driver.getDriver().get(url);
        driver.getDriver().manage().window().maximize();
        home = new HomePage(driver.getDriver());
        home.closeBanner();
    }

    @AfterTest()
    public void teardown() {
        driver.getDriver().quit();
    }

    protected <T> void checkThat(
            String description, T actualValue, Matcher<? super T> expectedValue) {
        Reporter.info(
                format("Checking that " + description.toLowerCase() + " [Expectation: %s]", expectedValue));
        try {
            MatcherAssert.assertThat(actualValue, expectedValue);
        } catch (AssertionError e) {
            Reporter.error(format("Assertion Error: [%s]", e.getMessage().replaceAll("\n", "")));
        }
    }

}
