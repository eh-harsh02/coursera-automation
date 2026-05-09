package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import browser.BrowserImplementation;
import config.ObjectReader;
import utils.ExcelUtils;

public class BaseTest {

	// ThreadLocal to handle separate driver instances for parallel execution
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	// Browser setup class reference
	BrowserImplementation browserImplementation;

	// Properties file reader reference
	ObjectReader objectReader;

	// Excel Start
	@BeforeSuite
	public void startExcel() {

		// Initialize Excel before running all tests
		ExcelUtils.initExcel();
	}

	// Setup browser before test execution
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
			driver.set(browserImplementation.getChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(browserImplementation.getEdgeDriver());
		}

		// Open URL
		getDriver().get(objectReader.getBaseUrl());
	}

	// Get current driver instance
	public static WebDriver getDriver() {
		return driver.get();
	}

	// Capture test result
	@AfterMethod
	public void captureResult(ITestResult result) {

		String testName = result.getName();
		String status;

		// Determine test status
		if (result.getStatus() == ITestResult.SUCCESS) {
			status = "PASS";
		} else if (result.getStatus() == ITestResult.FAILURE) {
			status = "FAIL";
		} else {
			status = "SKIP";
		}

		// Write result to Excel
		ExcelUtils.writeResult(testName, status);
	}

	@AfterTest
	public void tearDown() {

		// Close the browser
		getDriver().quit();

		// Remove driver instance to avoid memory issues
		driver.remove();
	}

	// Excel close
	@AfterSuite
	public void endExcel() {
		// Close Excel after test suite execution
		ExcelUtils.closeExcel();
	}

}