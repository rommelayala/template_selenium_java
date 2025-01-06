package com.example.utils;

import com.example.properties.EnvironmentProperties;
import io.testsmith.support.listeners.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.Reporter;

public class WebDriverSetup {

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  static EnvironmentProperties environmentProperties = new EnvironmentProperties();
  public static WebDriverSetup instance = new WebDriverSetup();

  public static WebDriverSetup getInstance(){
    return instance;
  }
  public static WebDriver getDriver() {
    return driver.get();
  }

  public void setDriver(WebDriver driverParam){
    driver.set(driverParam);
  }

  public static WebDriver setup() {
    //final String browser = System.getProperty("browser", "firefox");
    String browser = environmentProperties.getSystemProperty("browser", "firefox");
    try{

        WebDriver originalDriver = RommonSeleniumUtil.createDriver(browser);
        driver.set(
                new EventFiringDecorator(
                        new WebDriverLoggingListener(),
                        new SavepageSourceOnExceptionListener(originalDriver, "target/log1/pagesources"),
                        new SavescreenshotOnExceptionListener(originalDriver, "target/log1/screenshots"),
                        new HighlightElementsListener())
                        .decorate(originalDriver));
        WebDriver driverInstance = driver.get();
        //driverInstance.manage().window().maximize();
        driverInstance.manage().deleteAllCookies();
        driverInstance.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
        driverInstance.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

    } catch (TimeoutException e) {
      System.err.println("Page Load Timeout: " + e.getMessage());
      Reporter.log("PageLoad Timeout: " + e);
    }
    return driver.get();

  }

}
