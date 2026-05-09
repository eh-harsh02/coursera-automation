package pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class EnterprisePage {
	
	// WebDriver reference
    WebDriver driver;
    
    // Utility class for element actions
    ElementUtils elementUtils;
    
    // Javascript executor for scroll and click
    JavascriptExecutor jse;

    // Constructor
    public EnterprisePage(WebDriver driver) {
    		// Assign driver
        this.driver = driver;
        
        // Create ElementUtils object
        this.elementUtils = new ElementUtils(driver);
        
        // Typecast driver to JavascriptExecutor
        this.jse = (JavascriptExecutor) driver;
    }

    // Locators
    private By forCampusLink = By.xpath("//a[contains(text(),'For Campus')]");
    private By firstName = By.name("FirstName");
    private By lastName = By.name("LastName");
    private By email = By.name("Email");
    private By phone = By.name("Phone");

    private By institutionType = By.name("Institution_Type__c");
    private By institutionName = By.name("Company");

    private By jobRole = By.name("Title");
    private By department = By.name("Department");
    private By needs = By.id("Self_Reported_Needs__c");

    private By country = By.name("Country");
    private By state = By.name("State");

    private By submitButton = By.xpath("//button[@type='submit']");
    private By invalidEmailMsg = By.id("ValidMsgEmail");

    // Actions
    
    // Click on For Campus link
    public void clickForCampus() {
    		
    		// Click using js
        jse.executeScript("arguments[0].click();", elementUtils.waitForElementVisible(forCampusLink));

        // Wait for page load
        elementUtils.waitForElementVisible(firstName);
    }

    // Basic Details
    
    // Enter First Name
    public void enterFirstName(String fname) {
        elementUtils.sendKeys(firstName, fname);
    }
    
    // Enter Last Name
    public void enterLastName(String lname) {
        elementUtils.sendKeys(lastName, lname);
    }
    
    // Enter Email
    public void enterEmail(String emailValue) {
    		// Clear existing value
        elementUtils.waitForElementVisible(email).clear();
        
        // Enter email value
        elementUtils.sendKeys(email, emailValue);
    }
    
    // Enter Phone 
    public void enterPhone(String phoneValue) {
        elementUtils.sendKeys(phone, phoneValue);
    }

    // Institution Details
    public void selectInstitutionType(String value) {
        elementUtils.selectDropdown(institutionType, value);
    }
    
    // Enter institution name
    public void enterInstitutionName(String name) {
        elementUtils.sendKeys(institutionName, name);
    }

    // Role Details
    
    // Select job role
    public void selectJobRole(String value) {
        scrollTo(jobRole);
        elementUtils.selectDropdown(jobRole, value);
    }
    
    // Select department
    public void selectDepartment(String value) {
        elementUtils.selectDropdown(department, value);
    }
    
    // Select needs
    public void selectNeeds(String value) {
        elementUtils.selectDropdown(needs, value);
    }

    // Location
    
    // Select country
    public void selectCountry(String value) {
        elementUtils.selectDropdown(country, value);
    }
    
    // Select state
    public void selectState(String value) {
        elementUtils.selectDropdown(state, value);
    }

    // Submit
    public void clickSubmit() {
        scrollTo(submitButton);
        elementUtils.waitForElementClickable(submitButton).click();
    }

    // Validation
    public String getInvalidEmailMessage() {
        try {
            return elementUtils.waitForElementVisible(invalidEmailMsg).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        try {
            return elementUtils.waitForElementVisible(invalidEmailMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    

	// Text Fields
    
    // Get status of all text fields
	public Map<String, Boolean> getTextFieldsStatus() {
		
		// Create map to store field status
		Map<String, Boolean> fields = new LinkedHashMap<>();
		
		// Add each field with enabled status
		fields.put("First Name", elementUtils.isElementEnabled(firstName));
		fields.put("Last Name", elementUtils.isElementEnabled(lastName));
		fields.put("Email", elementUtils.isElementEnabled(email));
		fields.put("Phone", elementUtils.isElementEnabled(phone));
		fields.put("Institution Name", elementUtils.isElementEnabled(institutionName));
		
		// return map
		return fields;
	}

	// Dropdowns
	
	// Get status of all dropdowns
	public Map<String, Boolean> getDropdownsStatus() {
		
		// Create map to store dropdown status
		Map<String, Boolean> dropdowns = new LinkedHashMap<>();
		
		// Add each dropdown with enabled status
		dropdowns.put("Institution Type", elementUtils.isElementEnabled(institutionType));
		dropdowns.put("Job Role", elementUtils.isElementEnabled(jobRole));
		dropdowns.put("Department", elementUtils.isElementEnabled(department));
		dropdowns.put("Needs", elementUtils.isElementEnabled(needs));
		dropdowns.put("Country", elementUtils.isElementEnabled(country));
		
		// dropdowns.put("State", elementUtils.isElementEnabled(state));
		
		// return map
		return dropdowns;
	}
	
	// Check if state dropdown is enabled
	public boolean isStateDropdownEnabled() {
	    return elementUtils.waitForElementVisible(state).isEnabled();
	}


	// Submit Button
	public boolean isSubmitButtonEnabled() {
		return elementUtils.isElementEnabled(submitButton);
	}

	// Scroll to element using locator
    private void scrollTo(By locator) {
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", elementUtils.waitForElementVisible(locator));
    }
}