package com.example.testSpecs;

import com.example.properties.Environment;
import com.example.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SeleniumSpec extends WebDriverSetup {
  /**
   * Obtiene el webdriver desde WebDriverSetup, podria obtenerlo directamente porque es estatico pero por
   * legibilidad creo el atributo
   */
  WebDriver webDriver = getDriver();

  public SeleniumSpec() {}

  /**
   * Mostrar valores de un atributo System.out.println("EL valor del nuevo atributo es -> " +
   * Environment.getProperties().toto()); *
   */
  public void gogoUrlUsingProperties() {
    webDriver.get(Environment.getProperties().url());

  }

  public void iPauseForWaitSeconds(int waitSeconds) {
    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
  }
}