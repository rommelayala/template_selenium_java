package com.example.utils;

import com.example.listeners.SavepageSourceOnExceptionListener;
import com.example.listeners.SavescreenshotOnExceptionListener;
import com.example.listeners.SimpleEventListener;
import com.example.properties.EnvironmentProperties;
import io.testsmith.support.listeners.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.Reporter;

public class WebDriverSetup {
  // Singleton para toda la aplicación
  private static final WebDriverSetup INSTANCE = new WebDriverSetup();

  // ThreadLocal para manejar múltiples hilos
  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  private final EnvironmentProperties environmentProperties = new EnvironmentProperties();

  // Constructor privado para prevenir instanciación externa
  private WebDriverSetup() {}

  // Método para obtener la instancia Singleton
  public static WebDriverSetup getInstance() {
    return INSTANCE;
  }

  // Obtiene el WebDriver para el hilo actual
  public WebDriver getDriver() {
    if (driver.get() == null) {
      setupDriver();
    }
    return driver.get();
  }

  // Configura el WebDriver
  private void setupDriver() {
    String browser = environmentProperties.getSystemProperty("browser", "firefox");
    try {
      WebDriver originalDriver = RommonSeleniumUtil.createDriver(browser);

      // Configuración básica del driver
      originalDriver.manage().deleteAllCookies();
      originalDriver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
      originalDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

      // Decorar con listeners
      WebDriver decoratedDriver = new EventFiringDecorator<>(
              new WebDriverLoggingListener(),
              new SavepageSourceOnExceptionListener(originalDriver, "target/log1/pagesources"),
              new SavescreenshotOnExceptionListener(originalDriver, "target/log1/screenshots"),
              new HighlightElementsListener(),
              new SimpleEventListener()
      ).decorate(originalDriver);

      driver.set(decoratedDriver);

    } catch (TimeoutException e) {
      System.err.println("Page Load Timeout: " + e.getMessage());
      Reporter.log("Page Load Timeout: " + e);
    }
  }

  // Libera el WebDriver y recursos asociados
  public void tearDown() {
    WebDriver currentDriver = driver.get();
    if (currentDriver != null) {
      currentDriver.quit();
      driver.remove();
    }
  }
//  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//  static EnvironmentProperties environmentProperties = new EnvironmentProperties();
//  public static WebDriverSetup instance = new WebDriverSetup();
//
//  public static WebDriverSetup getInstance(){
//    return instance;
//  }
//  public static WebDriver getDriver() {
//    return driver.get();
//  }
//
//  public void setDriver(WebDriver driverParam){
//    driver.set(driverParam);
//  }
//
//  public static WebDriver setup() {
//    //final String browser = System.getProperty("browser", "firefox");
//    String browser = environmentProperties.getSystemProperty("browser", "firefox");
//    try{
//
//        WebDriver originalDriver = RommonSeleniumUtil.createDriver(browser);
//        //originalDriver.manage().window().maximize();
//        originalDriver.manage().deleteAllCookies();
//        originalDriver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
//        originalDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//
//        driver.set(
//                new EventFiringDecorator<>(
//                        new WebDriverLoggingListener(),
//                        new SavepageSourceOnExceptionListener(originalDriver, "target/log1/pagesources"),
//                        new SavescreenshotOnExceptionListener(originalDriver, "target/log1/screenshots"),
//                        new HighlightElementsListener(),
//                        new SimpleEventListener())
//                        .decorate(originalDriver));
//
//    } catch (TimeoutException e) {
//      System.err.println("Page Load Timeout: " + e.getMessage());
//      Reporter.log("PageLoad Timeout: " + e);
//    }
//    return driver.get();
//
//  }

}
