package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

    WebDriver driver;
    WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
    
	public List<WebElement> waitForAllElements(By locator) {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
    public void sendKeys(By locator, String value) {
        WebElement element = waitForElementVisible(locator);

        // Scroll to element
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element);

        // Click
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", element);
        }

        //  clear text
        element.clear();

        // Enter value
        element.sendKeys(value);
    }


    // Click after waiting for element to be clickable
    public void click(By locator) {
        waitForElementClickable(locator).click();
    }
    
	public void jsClick(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", element);
	}
	
    public void selectDropdown(By locator, String value) {
        WebElement element = waitForElementClickable(locator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element);

        element.click();
        element.sendKeys(value);
        element.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

}