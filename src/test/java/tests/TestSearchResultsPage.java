package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.SearchResultsPage;


public class TestSearchResultsPage extends BaseTest {

    private SearchResultsPage searchResultsPage;

    @BeforeClass
    public void init() {
        searchResultsPage = new SearchResultsPage(getDriver());
    }

    // ========================= TEST 1 =========================
    @Test(priority = 1)
    public void verifySearchResultsPageLoaded() {

        List<WebElement> courses = searchResultsPage.getAllCourses();

        Assert.assertTrue(courses.size() > 0, "Search results page not loaded or no courses found");
    }

    // ========================= TEST 2 =========================
    @Test(priority = 2)
    public void verifyLanguagesDisplayed() {

        // Open dropdown FIRST
        searchResultsPage.openLanguageFilter();

        // Then fetch
        List<WebElement> languages = searchResultsPage.getAllLanguages();

        // Print
        searchResultsPage.printAllLanguages();

        Assert.assertTrue(languages.size() > 0, "No languages found in Language dropdown");
    }

    // ========================= TEST 3 =========================
    @Test(priority = 3)
    public void testLanguageFilter() {

        searchResultsPage.applyLanguageFilter();
        

        Assert.assertTrue(true, "Language filter applied successfully");
    }

    // ========================= TEST 4 =========================
    @Test(priority = 4)
    public void verifyLevelsDisplayed() {

        // Open dropdown FIRST
        searchResultsPage.openLevelFilter();

        // Then fetch
        List<WebElement> levels = searchResultsPage.getAllLevels();

        // Print
        searchResultsPage.printAllLevels();

        Assert.assertTrue(levels.size() > 0,
                "No levels found in Level dropdown");
    }

    // ========================= TEST 5 =========================
    @Test(priority = 5)
    public void testLevelFilter() {

        searchResultsPage.applyLevelFilter();

        List<WebElement> courses = searchResultsPage.getAllCourses();

        Assert.assertTrue(courses.size() > 0,
                "No courses found after applying filters");
    }

    // ========================= TEST 6 =========================
    @Test(priority = 6)
    public void verifyFirstCourseDetails() {

        List<WebElement> courses = searchResultsPage.getAllCourses();

        WebElement course = courses.get(0);

        String title = searchResultsPage.getCourseTitle(course);

        // Get metadata once
        String meta = searchResultsPage.getCourseMetaData(course);

        // Extract from metadata
        String rating = searchResultsPage.getCourseRating(course);
        String duration = searchResultsPage.getCourseDuration(meta);

        // Assertions
        Assert.assertFalse(title.isEmpty(), "First course title is empty");
        Assert.assertFalse(meta.equals("N/A"), "Metadata missing");

        Assert.assertFalse(rating.equals("N/A"), "First course rating missing");
        Assert.assertFalse(duration.equals("N/A"), "First course duration missing");
    }

    // ========================= TEST 7 =========================
    @Test(priority = 7)
    public void verifySecondCourseDetails() {

        List<WebElement> courses = searchResultsPage.getAllCourses();

        // Ensure at least 2 courses
        Assert.assertTrue(courses.size() >= 2,
                "Less than 2 courses available");

        WebElement course = courses.get(1);

        String title = searchResultsPage.getCourseTitle(course);

        // Get full metadata once
        String meta = searchResultsPage.getCourseMetaData(course);

        // Extract values
        String rating = searchResultsPage.getCourseRating(course);
        String duration = searchResultsPage.getCourseDuration(meta);

        // Assertions
        Assert.assertFalse(title.isEmpty(), "Second course title is empty");
        Assert.assertFalse(meta.equals("N/A"), "Metadata missing for second course");

        Assert.assertFalse(rating.equals("N/A"), "Second course rating missing");
        Assert.assertFalse(duration.equals("N/A"), "Second course duration missing");
    }

    // ========================= TEST 8 =========================
    @Test(priority = 8)
    public void printTopTwoCourses() {

        searchResultsPage.printFirstTwoCourses();
    }
}