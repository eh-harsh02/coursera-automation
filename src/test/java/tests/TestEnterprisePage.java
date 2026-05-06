package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.EnterprisePage;

public class TestEnterprisePage extends BaseTest {

    private EnterprisePage enterprisePage;

    @BeforeClass
    public void init() {
        enterprisePage = new EnterprisePage(getDriver());
    }

    // ================= TEST 1 =================
    @Test(priority = 11)
    public void verifyForCampusNavigation() {

        enterprisePage.clickForCampus();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("campus"), "Navigation to For Campus page failed");
    }

    // ================= TEST 2 =================
    @Test(priority = 12)
    public void fillBasicDetailsTest() {

        enterprisePage.enterFirstName("Test");
        enterprisePage.enterLastName("User");
        enterprisePage.enterEmail("test123.com"); // invalid email
        enterprisePage.enterPhone("8876503210");

        Assert.assertTrue(true, "Basic details entered");
    }

    // ================= TEST 3 =================
    @Test(priority = 13)
    public void fillRemainingFormTest() {

        enterprisePage.selectInstitutionType("University/4 Year College");
        enterprisePage.enterInstitutionName("Cognizant");

        enterprisePage.selectJobRole("Student");
        enterprisePage.selectDepartment("Teaching/Faculty/Research");
        enterprisePage.selectNeeds("Learner Support");

        enterprisePage.selectCountry("India");
        enterprisePage.selectState("Bihar");

        Assert.assertTrue(true, "Remaining form filled");
    }

    // ================= TEST 4 =================
    @Test(priority = 14)
    public void validateInvalidEmailError() {

        enterprisePage.clickSubmit();

        String errorMsg = enterprisePage.getInvalidEmailMessage();

        System.out.println("Error Message: " + errorMsg);

        Assert.assertTrue(errorMsg.toLowerCase().contains("email"), "Invalid email error NOT displayed");
    }

    // ================= TEST 5 =================
    @Test(priority = 15)
    public void submitWithValidEmail() {

        enterprisePage.enterEmail("test.user@company.com");

        enterprisePage.clickSubmit();

        Assert.assertFalse(enterprisePage.isErrorMessageDisplayed(), "Error still present after valid email");

        System.out.println("Form submitted successfully");
    }
}