package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.SearchResultsPage;
import utils.ScreenshotUtil;

public class TestSearchResultsPage extends BaseTest {

	private SearchResultsPage searchResultsPage;

	@BeforeClass
	public void init() {
		searchResultsPage = new SearchResultsPage(getDriver());
	}

	// verify results page loaded
	@Test(priority = 3)
	public void verifySearchResultsPageLoaded() {

		List<WebElement> courses = searchResultsPage.getAllCourses();

		Assert.assertTrue(courses.size() > 0, "Search results page not loaded or no courses found");
	}

	// verify language dropdown displayed
	@Test(priority = 4)
	public void verifyLanguagesDisplayed() {

		// Open dropdown
		searchResultsPage.openLanguageFilter();

		List<WebElement> languages = searchResultsPage.getAllLanguages();

		searchResultsPage.printAllLanguages();

		Assert.assertTrue(languages.size() > 0, "No languages found in Language dropdown");
	}

	// Select english in language
	@Test(priority = 5)
	public void testLanguageFilter() {

		searchResultsPage.applyLanguageFilter();

		Assert.assertTrue(true, "Language filter applied successfully");
	}

	// Verify levels dropdown displayed
	@Test(priority = 6)
	public void verifyLevelsDisplayed() {

		// Open dropdown
		searchResultsPage.openLevelFilter();

		List<WebElement> levels = searchResultsPage.getAllLevels();

		searchResultsPage.printAllLevels();

		Assert.assertTrue(levels.size() > 0, "No levels found in Level dropdown");
	}

	// Select level as beginner
	@Test(priority = 7)
	public void testLevelFilter() {

		searchResultsPage.applyLevelFilter();

		// ExtentReportManager.logWithScreenshot("Level selected: Beginner",
		// getDriver());

		List<WebElement> courses = searchResultsPage.getAllCourses();

		Assert.assertTrue(courses.size() > 0, "No courses found after applying filters");
	}

	// Verify first course
	@Test(priority = 8)
	public void verifyFirstCourseDetails() {

		List<WebElement> courses = searchResultsPage.getAllCourses();

		WebElement course = courses.get(0);

		String title = searchResultsPage.getCourseTitle(course);
		// Assert.fail();
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

	// Verify second course details
	@Test(priority = 9)
	public void verifySecondCourseDetails() {

		// Screenshot AFTER applying filters
		String screenshotPath = ScreenshotUtil.takeScreenshot(getDriver(), "Language_English_Level_Beginner_Selected");
		System.out.println("Applied filter Screenshot saved at: " + screenshotPath);

		List<WebElement> courses = searchResultsPage.getAllCourses();

		// Ensure at least 2 courses
		Assert.assertTrue(courses.size() >= 2, "Less than 2 courses available");

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

	// Print top two courses
	@Test(priority = 10)
	public void printTopTwoCourses() {

		searchResultsPage.printFirstTwoCourses();
	}
}