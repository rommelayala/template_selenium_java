package com.example.testScripts;

import com.example.properties.Environment;
import org.openqa.selenium.WebDriver;

public class SeleniumSpec extends TestBase {
  /**
   * Obtiene el webdriver desde TestBase, podria obtenerlo directamente porque es estatico pero por
   * legibilidad creo el atributo
   */
  WebDriver webDriver = getDriver();

  public SeleniumSpec() {}

  /**
   * Mostrar valores de un atributo System.out.println("EL valor del nuevo atributo es -> " +
   * Environment.getProperties().toto()); *
   */
  public void openBrowserUsingProperties() {
    webDriver.get(Environment.getProperties().url());

  }
}
