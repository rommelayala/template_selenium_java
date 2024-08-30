package com.example.testScripts;

import com.example.properties.Environment;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

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
  public void openBrowserUsingProperties() {
    webDriver.get(Environment.getProperties().url());

  }
  public void iPauseUntilPressAnyKey() throws InterruptedException {
    System.out.println("Press Enter to continue...");
    try {
      // Esperar a que el usuario presione Enter
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    };
  }
}
