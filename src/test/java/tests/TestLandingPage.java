package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import extentReportListner.ExtentReportManager;
import pages.LandingPage;

//Landing test
public class TestLandingPage extends BaseTest {

	// Page class reference
	private LandingPage landingPage;

	// Init Page
	@BeforeClass
	public void init() {
		// Create Page Object
		landingPage = new LandingPage(getDriver());
	}

	// Verify Search Box Enabled
	@Test(priority = 0)
	public void verifySearchBoxEnabled() {

		boolean status = landingPage.isSearchBoxEnabled();

		ExtentReportManager.logStep("Search box enabled", status);

		Assert.assertTrue(status, "Search box is NOT enabled on Landing Page");
	}

	// Search Page
	@Test(priority = 1)
	public void testSearchCourses() {

		String course = "web development";

		landingPage.searchForCourse(course);

		String currentUrl = landingPage.getPageUrl();

		ExtentReportManager.getTest().info("Searched Course: " + course);
		ExtentReportManager.getTest().info("Navigated URL: " + currentUrl);

		// Validate search navigation
		Assert.assertTrue(currentUrl.toLowerCase().contains("search"), "Search results page NOT loaded");
	}
	
	// Verify page title
	@Test(priority = 2)
	public void verifyPageTitle() {

		String title = landingPage.getPageTitle();

		ExtentReportManager.getTest().info("Page Title: " + title);

		Assert.assertTrue(title.toLowerCase().contains("coursera"), "Page title is incorrect");
	}
}
