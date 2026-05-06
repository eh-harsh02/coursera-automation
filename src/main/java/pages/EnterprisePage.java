package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class EnterprisePage {

    WebDriver driver;
    ElementUtils elementUtils;
    JavascriptExecutor jse;

    // Constructor
    public EnterprisePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
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

    public void clickForCampus() {
        jse.executeScript("arguments[0].click();",
                elementUtils.waitForElementVisible(forCampusLink));

        // Wait for page load
        elementUtils.waitForElementVisible(firstName);
    }

    // Basic Details
    public void enterFirstName(String fname) {
        elementUtils.sendKeys(firstName, fname);
    }

    public void enterLastName(String lname) {
        elementUtils.sendKeys(lastName, lname);
    }

    public void enterEmail(String emailValue) {
        elementUtils.waitForElementVisible(email).clear();
        elementUtils.sendKeys(email, emailValue);
    }

    public void enterPhone(String phoneValue) {
        elementUtils.sendKeys(phone, phoneValue);
    }

    // Institution Details
    public void selectInstitutionType(String value) {
        elementUtils.selectDropdown(institutionType, value);
    }

    public void enterInstitutionName(String name) {
        elementUtils.sendKeys(institutionName, name);
    }

    // Role Details
    public void selectJobRole(String value) {
        scrollTo(jobRole);
        elementUtils.selectDropdown(jobRole, value);
    }

    public void selectDepartment(String value) {
        elementUtils.selectDropdown(department, value);
    }

    public void selectNeeds(String value) {
        elementUtils.selectDropdown(needs, value);
    }

    // Location
    public void selectCountry(String value) {
        elementUtils.selectDropdown(country, value);
    }

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

    public boolean isErrorMessageDisplayed() {
        try {
            return elementUtils.waitForElementVisible(invalidEmailMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Utility
    private void scrollTo(By locator) {
        jse.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                elementUtils.waitForElementVisible(locator));
    }
}