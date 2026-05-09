package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;

public class LandingPage {
	
	// WebDriver reference
	WebDriver driver;
	
	// Utility class for element actions
	ElementUtils elementUtils;
	
	// Constructor
	public LandingPage(WebDriver driver) {
		
		// Assign driver
		this.driver = driver;
		
		// Create ElementUtils object
		elementUtils = new ElementUtils(driver);
	}

	// Page elements
	private By searchBox = By.id("search-autocomplete-input");

	// Get current page URL
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	// Get current page title
	public String getPageTitle() {
		return driver.getTitle();
	}

	// Check if search box is enabled
	public boolean isSearchBoxEnabled() {
		return elementUtils.isElementEnabled(searchBox);
	}

	// Enter text in search box and submit
	public void searchForCourse(String courseName) {
		WebElement search = elementUtils.waitForElementVisible(searchBox);
		search.sendKeys(Keys.CONTROL + "a");
		search.sendKeys(Keys.DELETE);
		search.sendKeys(courseName);
		search.sendKeys(Keys.ENTER);

	}
}