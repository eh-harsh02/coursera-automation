package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandlingUtil {
	public static String getParentWindow(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public static void switchToNewWindow(WebDriver driver, String parentWindow) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public static void closeChildAndSwitchToParent(WebDriver driver, String parentWindow) {
        driver.close();
        driver.switchTo().window(parentWindow);
    }
}


