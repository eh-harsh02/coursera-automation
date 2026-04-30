package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SearchResultsPage;


public class CourseAutomationTest extends BaseTest {

    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void init() {
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Test(priority = 0)
    public void testSearchResultsPage() {
        searchResultsPage.getCourseDetails();
    }
}

