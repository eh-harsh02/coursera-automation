package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserImplementation {
	// WebDriver reference
	WebDriver driver;

	// Launch Chrome browser and maximize window
	public WebDriver getChromeDriver() {

		// Create ChromeDriver object
		driver = new ChromeDriver();

		// Maximize browser window
		driver.manage().window().maximize();

		// Return driver instance
		return driver;
	}

	// Launch Edge browser and maximize window
	public WebDriver getEdgeDriver() {

		// Create EdgeDriver object
		driver = new EdgeDriver();

		// Maximize browser window
		driver.manage().window().maximize();

		// Return driver instance
		return driver;
	}
}
