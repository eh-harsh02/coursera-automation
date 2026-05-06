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

    WebDriver driver;
    ElementUtils elementUtils;
    JavascriptExecutor jse;
    WebDriverWait wait;

    public CourseDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ================= LOCATORS =================

    private By enrollButton = By.xpath("//span[@data-test='enroll-button-label']");
    
    private void scrollToElement(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}
    
    
    // ================= ACTION METHODS =================

    // Click course
    public void openCourse(int courseIndex) {

        WebElement courseCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='cds-CommonCard-clickArea'])[" + courseIndex + "]")));

        // Scroll to element
        scrollToElement(courseCard);

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(courseCard));

        try {
            courseCard.click();
        } catch (Exception e) {
            jse.executeScript("arguments[0].click();", courseCard);
        }
    }

    // Switch to child window
    public String switchToCourseWindow() {

        String parentWindow = WindowHandlingUtil.getParentWindow(driver);

        WindowHandlingUtil.switchToNewWindow(driver, parentWindow);

        return parentWindow;
    }

    // Close child window
    public void closeCourseWindow(String parentWindow) {
        WindowHandlingUtil.closeChildAndSwitchToParent(driver, parentWindow);
    }

    // ================= VALIDATION METHODS =================

    // Check enroll button enabled
    public boolean isEnrollButtonEnabled() {
        try {
            WebElement enrollBtn = elementUtils.waitForElementVisible(enrollButton);
            return enrollBtn.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

}