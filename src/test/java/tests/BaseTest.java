package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import browser.BrowserImplementation;
import config.ObjectReader;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    BrowserImplementation browserImplementation;
    ObjectReader objectReader;

    @Parameters("browser")
    @BeforeTest
    public void setUp(@Optional("chrome") String browserName) throws IOException {

        browserImplementation = new BrowserImplementation();
        objectReader = new ObjectReader();

        if (browserName.equalsIgnoreCase("chrome")) {
            driver.set(browserImplementation.getChromeDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(browserImplementation.getEdgeDriver());
        }

        getDriver().get(objectReader.getBaseUrl());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterTest
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }
}