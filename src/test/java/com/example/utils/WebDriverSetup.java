package com.example.utils;

import com.example.properties.EnvironmentProperties;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.testsmith.support.listeners.*;
import com.example.utils.RommonSeleniumUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public abstract class WebDriverSetup {

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  static EnvironmentProperties environmentProperties = new EnvironmentProperties();

  public static WebDriver getDriver() {
    return driver.get();
  }

  @BeforeClass
  public static void setup() {
    //final String browser = System.getProperty("browser", "firefox");
    String browser = environmentProperties.getSystemProperty("browser", "chrome_headless");
    if (browser != null) {
      WebDriver originalDriver = RommonSeleniumUtil.createDriver(browser);
      driver.set(
          new EventFiringDecorator(
                  new WebDriverLoggingListener(),
                  new SavepageSourceOnExceptionListener(originalDriver, "target/log/pagesources"),
                  new SavescreenshotOnExceptionListener(originalDriver, "target/log/screenshots"),
                  new HighlightElementsListener())
              .decorate(originalDriver));
    } else {
      System.out.println("url is null");
    }
  }

  @AfterClass
  public static void teardown() {
    getDriver().quit();
  }

  // TODO: REvizar estos metodos
  @After
  public void tearDown(Scenario scenario) throws IOException, InterruptedException {

    if (scenario.isFailed()) {
      // take a print screen
      getFailureScreenShot(scenario);
    }
    // driver.quit();

  }

  private void getFailureScreenShot(Scenario scenario) {
    try {
      if (driver instanceof TakesScreenshot screenshot) {
          byte[] screenshot_bytes = screenshot.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot_bytes, "image/png", "Failure Screenshot");
      } else {
        System.err.println("Driver does not support screenshots.");
      }

    } catch (Exception e) {
      System.err.println("Failed to take screenshot: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
