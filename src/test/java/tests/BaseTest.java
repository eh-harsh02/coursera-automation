package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import browser.BrowserImplementation;
import config.ObjectReader;

public class BaseTest {
	// WebDriver reference
	WebDriver driver = null;

	// Browser setup class reference
	BrowserImplementation browserImplementation;

	// Properties file reader reference
	ObjectReader objectReader;

	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) throws IOException {

		// Create browser implementation object
		browserImplementation = new BrowserImplementation();

		// Read data from object.properties file
		objectReader = new ObjectReader();

		// Read browser name from properties file
		// String browserName = objectReader.getBrowser();

		// Launch browser based on browser name
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = browserImplementation.getChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = browserImplementation.getEdgeDriver();
		} else {
			throw new RuntimeException("Invalid browser name");
		}

		// Open URL
		driver.get(objectReader.getBaseUrl());
	}

	@AfterTest
	public void tearDown() {

		// Final screenshot after complete automation
//		String finalScreenshotPath = ScreenshotUtil.takeScreenshot(driver, "Final_Execution_State");

		// Close the browser
		driver.quit();
	}
}
