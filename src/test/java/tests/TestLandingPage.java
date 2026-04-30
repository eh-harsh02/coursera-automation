package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LandingPage;

public class TestLandingPage extends BaseTest {

    LandingPage landingPage;

    @BeforeMethod
    public void init() {
        landingPage = new LandingPage(driver);
    }

    @Test(priority = 0)
    public void testSearchWithEmptyInput() {
        String urlBefore = driver.getCurrentUrl();
        landingPage.searchEmptyString();
        String urlAfter = driver.getCurrentUrl();

        Assert.assertEquals(urlAfter, urlBefore,
                "Page should not change when search is empty");
    }

    @Test(priority = 1)
    public void testSearchWithInvalidKeyword() {
        landingPage.searchForCourse("asdfghjkl");

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("search"),
                "Search page not loaded for invalid input");
    }

    @Test(priority = 2)
    public void testSearchCourses() {
        Assert.assertTrue(landingPage.isSearchBoxEnabled(),
                "Search box is not enabled on Landing Page");

        landingPage.searchForCourse("web development");
    }
}
