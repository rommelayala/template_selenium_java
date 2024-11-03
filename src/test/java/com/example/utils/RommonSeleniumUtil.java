package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
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
}
