package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LandingPage;


public class TestLandingPage extends BaseTest {

    private LandingPage landingPage;

    @BeforeClass
    public void init() {
        landingPage = new LandingPage(getDriver());
    }

    @Test(priority = 0)
    public void testSearchCourses() {
        Assert.assertTrue(landingPage.isSearchBoxEnabled(), "Search box is not enabled on Landing Page");

        landingPage.searchForCourse("web development");
    }
}
