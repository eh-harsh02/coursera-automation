//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import utils.ElementUtils;
//import utils.WindowHandlingUtil;
//
//public class CourseDetailsPage {
//
//    WebDriver driver;
//    ElementUtils elementUtils;
//    JavascriptExecutor jse;
//    WebDriverWait wait;
//
//    public CourseDetailsPage(WebDriver driver) {
//        this.driver = driver;
//        this.elementUtils = new ElementUtils(driver);
//        this.jse = (JavascriptExecutor) driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//    }
//
//    //LOCATORS 
//
//    private By enrollButton = By.xpath("//span[@data-test='enroll-button-label']");
//    
//    private void scrollToElement(WebElement element) {
//		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
//	}
//    
//    
//    // ACTION METHODS
//
//    // Click course
//    public void openCourse(int courseIndex) {
//
//        WebElement courseCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='cds-CommonCard-clickArea'])[" + courseIndex + "]")));
//
//        // Scroll to element
//        scrollToElement(courseCard);
//
//        // Wait until clickable
//        wait.until(ExpectedConditions.elementToBeClickable(courseCard));
//
//        try {
//            courseCard.click();
//        } catch (Exception e) {
//            jse.executeScript("arguments[0].click();", courseCard);
//        }
//    }
//
//    // Switch to child window
//    public String switchToCourseWindow() {
//
//        String parentWindow = WindowHandlingUtil.getParentWindow(driver);
//
//        WindowHandlingUtil.switchToNewWindow(driver, parentWindow);
//
//        return parentWindow;
//    }
//
//    // Close child window
//    public void closeCourseWindow(String parentWindow) {
//        WindowHandlingUtil.closeChildAndSwitchToParent(driver, parentWindow);
//    }
//
//    // VALIDATION METHODS
//
//    // Check enroll button enabled
//    public boolean isEnrollButtonEnabled() {
//        try {
//            WebElement enrollBtn = elementUtils.waitForElementVisible(enrollButton);
//            return enrollBtn.isEnabled();
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//}


package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ElementUtils;
import utils.WindowHandlingUtil;

public class CourseDetailsPage {

    // WebDriver reference
    WebDriver driver;

    // Utility class for element actions
    ElementUtils elementUtils;

    // Javascript executor for special actions
    JavascriptExecutor jse;

    // WebDriverWait for explicit wait
    WebDriverWait wait;

    // Constructor to initialize driver and utilities
    public CourseDetailsPage(WebDriver driver) {
        this.driver = driver;

        // Create ElementUtils object
        this.elementUtils = new ElementUtils(driver);

        // Typecasting driver to JavascriptExecutor
        this.jse = (JavascriptExecutor) driver;

        // Create wait object with 15 seconds duration
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // LOCATORS 

    // Locator for enroll button
    private By enrollButton = By.xpath("//span[@data-test='enroll-button-label']");
    
    // Scroll to a specific element on page
    private void scrollToElement(WebElement element) {

        // Scroll element to center of screen
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
    
    
    // ACTION METHODS

    // Click course based on index
    public void openCourse(int courseIndex) {

        // Wait until course card is present on page
        WebElement courseCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='cds-CommonCard-clickArea'])[" + courseIndex + "]")));

        // Scroll to course element
        scrollToElement(courseCard);

        // Wait until element is clickable
        wait.until(ExpectedConditions.elementToBeClickable(courseCard));

        try {
            // Try normal click
            courseCard.click();

        } catch (Exception e) {

            // If normal click fails, use JavaScript click
            jse.executeScript("arguments[0].click();", courseCard);
        }
    }

    // Switch to new course window
    public String switchToCourseWindow() {

        // Get parent window id
        String parentWindow = WindowHandlingUtil.getParentWindow(driver);

        // Switch to new opened window
        WindowHandlingUtil.switchToNewWindow(driver, parentWindow);

        // Return parent window id
        return parentWindow;
    }

    // Close course window and switch back to parent
    public void closeCourseWindow(String parentWindow) {

        // Close child window and switch to parent
        WindowHandlingUtil.closeChildAndSwitchToParent(driver, parentWindow);
    }

    // VALIDATION METHODS

    // Check if enroll button is enabled
    public boolean isEnrollButtonEnabled() {
        try {
            // Wait until enroll button is visible
            WebElement enrollBtn = elementUtils.waitForElementVisible(enrollButton);

            // Return true if enabled
            return enrollBtn.isEnabled();

        } catch (Exception e) {

            // Return false if any exception occurs
            return false;
        }
    }

}