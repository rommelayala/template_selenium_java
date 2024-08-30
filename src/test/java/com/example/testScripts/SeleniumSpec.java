package com.example.testScripts;

import com.example.properties.Environment;
import org.openqa.selenium.WebDriver;

public class SeleniumSpec extends AbstractWebdriverBase {

  public SeleniumSpec() {
    super(TestBase.getDriver());
  }

  /**
   * Mostrar valores de un atributo System.out.println("EL valor del nuevo atributo es -> " +
   * Environment.getProperties().toto()); *
   */
  public void openBrowserUsingProperties() {
    driver.get(Environment.getProperties().url());
    // Mostrar valores de un atributo
    // System.out.println("EL valor del nuevo atributo es -> " +
    // Environment.getProperties().toto());
  }
}
