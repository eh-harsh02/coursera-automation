package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandlingUtil {
	
	// Get parent window id
	public static String getParentWindow(WebDriver driver) {
		
		// Return current window handle
		return driver.getWindowHandle();
	}
	
	// Switch to newly opened window
	public static void switchToNewWindow(WebDriver driver, String parentWindow) {
		
		// Get all window handles
		Set<String> windows = driver.getWindowHandles();
		
		// Loop through all windows
		for (String window : windows) {
			
			// Check if it is not parent window
			if (!window.equals(parentWindow)) {
				
				// Switch to new window
				driver.switchTo().window(window);
				
				// Exit loop after switching
				break;
			}
		}
	}
	
	// Close child window and switch to parent window
	public static void closeChildAndSwitchToParent(WebDriver driver, String parentWindow) {
		
		// Close current window
		driver.close();
		
		// Switch back to parent window
		driver.switchTo().window(parentWindow);
	}
}
