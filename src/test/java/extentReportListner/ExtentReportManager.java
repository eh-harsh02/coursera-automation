package extentReportListner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import tests.BaseTest;
import utils.ScreenshotUtil;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	// Getter for tests to log steps
	public static ExtentTest getTest() {
		return extentTest.get();
	}

	// reusable logger
	public static void logStep(String stepName, boolean status) {
		if (status) {
			getTest().pass(stepName);
		} else {
			getTest().fail(stepName);
		}
	}

	public static void logWithScreenshot(String message, WebDriver driver) {

		getTest().info(message);

		String path = ScreenshotUtil.takeScreenshot(driver, message);

		try {
			getTest().addScreenCaptureFromPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Report Initialization
	@Override
	public void onStart(ITestContext context) {

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReports/myReport.html");

		sparkReporter.config().setDocumentTitle("Coursera Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// System Info
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Harsh Raj");
		extent.setSystemInfo("OS", "Windows 10");
	}

	// Create Test BEFORE execution
	@Override
	public void onTestStart(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

		ExtentTest test = extent.createTest(result.getName() + " [" + browser + "]");

		extentTest.set(test);
		getTest().info("Test Started: " + result.getName() + " on " + browser);
	}

	// PASS
	@Override
	public void onTestSuccess(ITestResult result) {

		getTest().log(Status.PASS, "Test PASSED: " + result.getName());
	}

	// FAIL + Screenshot
	@Override
	public void onTestFailure(ITestResult result) {

		getTest().log(Status.FAIL, "Test FAILED: " + result.getName());
		getTest().fail(result.getThrowable());

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			getTest().fail("Failure Reason: " + throwable.getMessage());
		}

		WebDriver driver = BaseTest.getDriver();

		if (driver != null) {
			String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());

			try {
				getTest().fail("Screenshot on Failure").addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			getTest().warning("Driver is NULL - Screenshot not captured");
		}
	}

	// SKIP
	@Override
	public void onTestSkipped(ITestResult result) {

		getTest().log(Status.SKIP, "Test SKIPPED: " + result.getName());
	}

	// Flush Report
	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}
}
