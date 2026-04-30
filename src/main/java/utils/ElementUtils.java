package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

    WebDriver driver;
    WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element to be visible and return it
    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable and return it
    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Check if element is enabled
    public boolean isElementEnabled(By locator) {
        return waitForElementVisible(locator).isEnabled();
    }

    // Check if element is displayed
    public boolean isElementDisplayed(By locator) {
        return waitForElementVisible(locator).isDisplayed();
    }

    // Send keys after waiting for visibility
	public void sendKeys(By locator, String value) {
		WebElement element = waitForElementVisible(locator);

		element.sendKeys(Keys.CONTROL + "a"); // select all text
		element.sendKeys(Keys.DELETE); // delete text
		element.sendKeys(value);
	}


    // Click after waiting for element to be clickable
    public void click(By locator) {
        waitForElementClickable(locator).click();
    }
}