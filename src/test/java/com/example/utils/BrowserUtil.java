package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserUtil {

  private static final String GRID_URL = "http://localhost:4444/wd/hub";

  public static WebDriver createDriver(String browser) {
    WebDriver driver = null;
      String sGridflag = "false";
    try {
      if (sGridflag == "true") {
        switch (Browser.valueOf(browser.toUpperCase())) {
          case CHROME:
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("-disable-search-engine-choice-screen");
            driver = new RemoteWebDriver(new URL(GRID_URL), chromeOptions);
            break;
          case FIREFOX:
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL(GRID_URL), firefoxOptions);
            break;
          case EDGE:
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            driver = new RemoteWebDriver(new URL(GRID_URL), edgeOptions);
            break;
          case CHROME_HEADLESS:
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptionsHeadless = new ChromeOptions();
            chromeOptionsHeadless.addArguments("--headless");
            driver = new RemoteWebDriver(new URL(GRID_URL), chromeOptionsHeadless);
            break;
          case FIREFOX_HEADLESS:
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptionsHeadless = new FirefoxOptions();
            firefoxOptionsHeadless.addArguments("-headless");
            driver = new RemoteWebDriver(new URL(GRID_URL), firefoxOptionsHeadless);
            break;
          default:
            throw new IllegalArgumentException("Browser not supported");
        }
      } else {
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
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return driver;
  }
}
