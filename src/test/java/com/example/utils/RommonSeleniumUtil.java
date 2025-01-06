package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

public class RommonSeleniumUtil {

  private final Logger logger = LogManager.getLogger("com.example.utils.RommonSeleniumUtil");

  public RommonSeleniumUtil() {}

  public String getBrowserName() {
    return this.getBrowserName();
  }

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

  public List<WebElement> getWebElementBy(String by, String value, WebDriver webDriver) {
    List<WebElement> webElements = null;
    this.logger.debug("Locating {} by {}", by, value);

    switch (by.toLowerCase()) {
      case "id":
        webElements = webDriver.findElements(By.id(value));
        break;
      case "name":
        webElements = webDriver.findElements(By.name(value));
        break;
      case "classname":
        webElements = webDriver.findElements(By.className(value));
        break;
      case "tagname":
        webElements = webDriver.findElements(By.tagName(value));
        break;
      case "css":
        webElements = webDriver.findElements(By.cssSelector(value));
        break;
      case "xpath":
        webElements = webDriver.findElements(By.xpath(value));
        break;
      case "linktext":
        webElements = webDriver.findElements(By.linkText(value));
        break;
      case "partiallinktext":
        webElements = webDriver.findElements(By.partialLinkText(value));
        break;
      default:
        throw new IllegalArgumentException("Selector type '" + by + "' is not supported.");
    }

    return webElements;
  }
}
