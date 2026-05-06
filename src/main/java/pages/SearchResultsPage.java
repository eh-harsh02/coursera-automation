package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;

public class SearchResultsPage {

    WebDriver driver;
    ElementUtils elementUtils;
    JavascriptExecutor jse;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        jse = (JavascriptExecutor) driver;
    }

    // ========================= LOCATORS =========================

    // Filters
    private By languageDropdown = By.xpath("//button[@data-testid='filter-dropdown-language']");
    private By englishCheckbox = By.xpath("//div[contains(@data-testid, 'language:English')]");
    private By viewButton = By.xpath("//button[@data-testid='filter-view-button']");

    private By levelDropdown = By.xpath("//button[@data-testid='filter-dropdown-productDifficultyLevel']");
    private By beginnerCheckbox = By.xpath("//div[contains(@data-testid, 'productDifficultyLevel:Beginner')]");

    // Dropdown options (for language & level)
    private By dropdownOptions = By.xpath("//span[@class='css-s63saa']/parent::span");

    // Courses
//    private By courseCards = By.xpath("//div[contains(@class,'cds-CommonCard')]");
	private By courseCards = By.xpath("//div[contains(@class,'cds-CommonCard') and .//h3]");
    private By courseTitle = By.xpath(".//h3");
    
	private By courseMetaData = By.xpath(".//p");
	private By courseRating = By.xpath(".//span[contains(@class,'css-4s48ix')]");


    // ========================= SCROLL METHOD =========================

	private void scrollToElement(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}

    // ========================= FILTER METHODS =========================

    public void openLanguageFilter() {
    		
    	
    		
        WebElement element = elementUtils.waitForElementVisible(languageDropdown);

        // Scroll into language dropdown
        scrollToElement(element);

        elementUtils.jsClick(element);
    }
    
	// Language filter
	public void applyLanguageFilter() {

		openLanguageFilter();
		elementUtils.click(englishCheckbox);
		elementUtils.click(viewButton);

	}
    
    
	public void openLevelFilter() {

		WebElement element = elementUtils.waitForElementVisible(levelDropdown);
		elementUtils.jsClick(element);
	}

    
	// Level filter
	public void applyLevelFilter() {

		openLevelFilter();
		elementUtils.click(beginnerCheckbox);
		elementUtils.click(viewButton);
	}

    // ========================= LANGUAGE METHODS =========================

    public List<WebElement> getAllLanguages() {
        return elementUtils.waitForAllElements(dropdownOptions);
    }

    public void printAllLanguages() {

        openLanguageFilter();

        List<WebElement> languages = getAllLanguages();

        System.out.println("===== AVAILABLE LANGUAGES =====");

        for (WebElement lang : languages) {
            String text = lang.getText().replace("\n", " ").trim();
            System.out.println(text);
        }

        System.out.println("Total Languages: " + languages.size());
    }

    // ========================= LEVEL METHODS =========================

    public List<WebElement> getAllLevels() {
        return elementUtils.waitForAllElements(dropdownOptions);
    }

    public void printAllLevels() {

        openLevelFilter();

        List<WebElement> levels = getAllLevels();

        System.out.println("===== AVAILABLE LEVELS =====");

        for (WebElement level : levels) {
            String text = level.getText().replace("\n", " ").trim();
            System.out.println(text);
        }

        System.out.println("Total Levels: " + levels.size());
    }

    // ========================= COURSE EXTRACTION =========================

	public List<WebElement> getAllCourses() {

		List<WebElement> allCourses = elementUtils.waitForAllElements(courseCards);

		List<WebElement> visibleCourses = new ArrayList<>();

		for (WebElement course : allCourses) {
			if (course.isDisplayed()) {
				visibleCourses.add(course);
			}
		}

		return visibleCourses;
	}

    public String getCourseTitle(WebElement course) {
        try {
            return course.findElement(courseTitle).getText();
        } catch (Exception e) {
            return "N/A";
        }
    }


    public String getCourseMetaData(WebElement course) {

		try {
			List<WebElement> metaElements = course.findElements(courseMetaData);

			StringBuilder metaText = new StringBuilder();

			for (WebElement el : metaElements) {
				metaText.append(el.getText()).append(" ");
			}

			String finalMeta = metaText.toString().trim();

			return finalMeta;

		} catch (Exception e) {
			return "N/A";
		}
	}

	// Extract rating
	public String getCourseRating(WebElement course) {

		try {
			return course.findElement(courseRating).getText().trim();
		} catch (Exception e) {
			return "N/A";
		}
	}

	// Extract duration
	public String getCourseDuration(String meta) {

		if (meta == null || meta.isEmpty())
			return "N/A";

		String[] parts = meta.split("·");

		for (String part : parts) {

			if (part.toLowerCase().contains("week") || part.toLowerCase().contains("month")) {
				return part.trim();
			}
		}

		return "N/A";
	}
   


    // ========================= FINAL OUTPUT =========================

    public void printFirstTwoCourses() {

        List<WebElement> courses = getAllCourses();

        System.out.println("===== TOP 2 COURSES =====");

        for (int i = 0; i < Math.min(2, courses.size()); i++) {

            WebElement course = courses.get(i);

            String title = getCourseTitle(course);
            String meta = getCourseMetaData(course);
            
            String rating = getCourseRating(course);
            String duration = getCourseDuration(meta);

            System.out.println("Course " + (i + 1));
            System.out.println("Title: " + title);
            System.out.println("Rating: " + rating);
            System.out.println("Duration: " + duration);
            System.out.println("----------------------------");
            
			
        }
    }
}