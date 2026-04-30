package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;

public class SearchResultsPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }

    // Page elements
    private By languageDropdown = By.xpath("//button[@data-testid='filter-dropdown-language']");
    private By languageOptions = By.xpath("//span[@class='css-s63saa']/parent::span");
    private By englishCheckbox = By.xpath("//div[contains(@data-testid, 'language:English')]");
    private By viewButton = By.xpath("//button[@data-testid='filter-view-button']");

    private By levelDropdown = By.xpath("//button[@data-testid='filter-dropdown-productDifficultyLevel']");
    private By levelOptions = By.xpath("//span[@class='css-s63saa']/parent::span");
    private By beginnerCheckbox = By.xpath("//div[contains(@data-testid, 'productDifficultyLevel:Beginner')]");

    // Apply language and level filters and print available values
    public void getCourseDetails() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // Scroll to Language dropdown
        WebElement languageMenu = elementUtils.waitForElementVisible(languageDropdown);
        jse.executeScript("arguments[0].scrollIntoView(true);", languageMenu);
        elementUtils.click(languageDropdown);
        
        System.out.println("Available languages");
        // Fetch and print available languages
        List<WebElement> availableLanguages = driver.findElements(languageOptions);
		for (WebElement language : availableLanguages) {
			String text = language.getText();
			text = text.replace("\n", " ").trim();
			System.out.println(text);

		}
		
        // Select English and apply filter
        elementUtils.click(englishCheckbox);
        elementUtils.click(viewButton);

        // Open Level dropdown
        elementUtils.click(levelDropdown);
        
        System.out.println("Available levels");
        // Fetch and print available levels
        List<WebElement> availableLevels = driver.findElements(levelOptions);
		for (WebElement level : availableLevels) {
			String text = level.getText();
			text = text.replace("\n", " ").trim();
			System.out.println(text);
		}

        // Select Beginner level
        elementUtils.click(beginnerCheckbox);
        
//		List<WebElement> courseDetails = driver.findElements(By.xpath("//h3[@class='cds-CommonCard-title css-6ecy9b']"));
    }
}