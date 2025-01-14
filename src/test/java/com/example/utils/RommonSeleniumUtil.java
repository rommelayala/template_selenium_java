package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class RommonSeleniumUtil {

    public static WebDriver createDriver(String browser) {
        WebDriver driver = null;
        switch (Browser.valueOf(browser.toUpperCase())) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("-disable-search-engine-choice-screen");
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case CHROME_HEADLESS:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions_headless = new ChromeOptions();
                chromeOptions_headless.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions_headless);
                break;
            case FIREFOX_HEADLESS:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                driver = new FirefoxDriver(options);
                break;
        }
        return driver;
    }
    public WebElement getWebElementBy(String by, String value, WebDriver webDriver) {
        WebElement element;
        switch (by.toLowerCase()) {
            case "id":
                element = webDriver.findElement(By.id(value));
                break;
            case "name":
                element = webDriver.findElement(By.name(value));
                break;
            case "classname":
                element = webDriver.findElement(By.className(value));
                break;
            case "tagname":
                element = webDriver.findElement(By.tagName(value));
                break;
            case "css":
            case "cssselector":
                element = webDriver.findElement(By.cssSelector(value));
                break;
            case "xpath":
                element = webDriver.findElement(By.xpath(value));
                break;
            case "linktext":
                element = webDriver.findElement(By.linkText(value));
                break;
            case "partiallinktext":
                element = webDriver.findElement(By.partialLinkText(value));
                break;
            default:
                throw new IllegalArgumentException("Selector type '" + by + "' is not supported.");
        }
        return element;
    }
}
