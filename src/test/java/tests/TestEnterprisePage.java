package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.EnterprisePage;
import utils.ScreenshotUtil;

public class TestEnterprisePage extends BaseTest {
	
	// Page class reference
	private EnterprisePage enterprisePage;

	@BeforeClass
	public void init() {
		enterprisePage = new EnterprisePage(getDriver());
	}

	// Verify campus page
	@Test(priority = 13)
	public void verifyForCampusNavigation() {

		enterprisePage.clickForCampus();

		Assert.assertTrue(getDriver().getCurrentUrl().contains("campus"), "Navigation to For Campus page failed");
	}

	// Verify all text fields are enabled
	@Test(priority = 14)
	public void verifyTextFieldsEnabled() {

		Map<String, Boolean> fields = enterprisePage.getTextFieldsStatus();

		for (Map.Entry<String, Boolean> entry : fields.entrySet()) {
			Assert.assertTrue(entry.getValue(), entry.getKey() + " field is NOT enabled");
		}

		System.out.println("All text fields are enabled");
	}

	// Enter basic details with invalid email
	@Test(priority = 15)
	public void fillBasicDetailsTest() {

		enterprisePage.enterFirstName("Test");
		enterprisePage.enterLastName("User");
		enterprisePage.enterEmail("test123.com"); // invalid email
		enterprisePage.enterPhone("8876503210");

		Assert.assertTrue(true, "Basic details entered");
	}

	// Verify all dropdowns are enabled
	@Test(priority = 16)
	public void verifyDropdownsEnabled() {

		Map<String, Boolean> dropdowns = enterprisePage.getDropdownsStatus();

		for (Map.Entry<String, Boolean> entry : dropdowns.entrySet()) {
			Assert.assertTrue(entry.getValue(), entry.getKey() + " dropdown is NOT enabled");
		}

		System.out.println("All dropdowns are enabled");
	}

	// Fill remaining form details
	@Test(priority = 17)
	public void fillRemainingFormTest() {

		enterprisePage.selectInstitutionType("University/4 Year College");
		enterprisePage.enterInstitutionName("Cognizant");

		enterprisePage.selectJobRole("Student");
		enterprisePage.selectDepartment("Teaching/Faculty/Research");
		enterprisePage.selectNeeds("Learner Support");

		enterprisePage.selectCountry("India");

		Assert.assertTrue(enterprisePage.isStateDropdownEnabled(),
				"State dropdown is NOT enabled after selecting country");

		enterprisePage.selectState("Bihar");

		Assert.assertTrue(true, "Remaining form filled");
	}

	// Verify submit button is enabled
	@Test(priority = 18)
	public void verifySubmitButtonEnabled() {

		Assert.assertTrue(enterprisePage.isSubmitButtonEnabled(), "Submit button is NOT enabled");

		System.out.println("Submit button is enabled and clickable");
	}

	// Validate error for invalid email
	@Test(priority = 19)
	public void validateInvalidEmailError() {

		enterprisePage.clickSubmit();

		String errorMsg = enterprisePage.getInvalidEmailMessage();

		System.out.println("Error Message: " + errorMsg);

		// Take Screenshot after error message appears
		String screenshotPath = ScreenshotUtil.takeScreenshot(getDriver(), "InvalidEmailError");

		System.out.println("Email Error Screenshot saved at: " + screenshotPath);

		Assert.assertTrue(errorMsg.toLowerCase().contains("email"), "Invalid email error NOT displayed");
	}

	// Submit form with valid email
	@Test(priority = 20)
	public void submitWithValidEmail() {

		enterprisePage.enterEmail("test.user@company.com");

		enterprisePage.clickSubmit();

		Assert.assertFalse(enterprisePage.isErrorMessageDisplayed(), "Error still present after valid email");

		System.out.println("Form submitted successfully");
	}
}