package com.example.testSpecs;

import com.example.properties.EnvironmentProperties;
import com.example.utils.RommonSeleniumUtil;
import com.example.utils.Wait;
import com.example.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;

import java.time.Duration;
import java.util.List;

public class SeleniumSpec extends WebDriverSetup {
  /**
   * Obtiene el webdriver desde WebDriverSetup, podria obtenerlo directamente porque es estatico
   * pero por legibilidad creo el atributo
   */
  WebDriver webDriver = getDriver();

  Wait wait = new Wait(webDriver);
  RommonSeleniumUtil rSeleniumUtil = new RommonSeleniumUtil();
  static EnvironmentProperties environmentProperties = new EnvironmentProperties();

  public SeleniumSpec() {}

  /**
   * Mostrar valores de un atributo System.out.println("EL valor del nuevo atributo es -> " +
   * Environment.getProperties().toto()); *
   */
  public void gotoUrlUsingProperties() {
    String url = environmentProperties.getSystemProperty("url");
    webDriver.get(url);
  }

  public void gogoUrlUsingFeatureString(String url) {
    webDriver.get(url);
  }

  public void iPauseForWaitSeconds(int waitSeconds) {
    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
  }

  public void iGoToThisUrl(String url) {
    webDriver.get(url);
  }


  public void iTypeTextOnInput(String text, String selector) {
    String[] method_selector = selector.split(":");
    wait.force_sleep(500);
    List<WebElement> wel =
        rSeleniumUtil.getWebElementBy(method_selector[0], method_selector[1], webDriver);

    wel.getFirst().sendKeys(text);
  }

  public void clickOnTheButtonWith(String selector) {
    String[] method_selector = selector.split(":");
    List<WebElement> wel =
        rSeleniumUtil.getWebElementBy(method_selector[0], method_selector[1], webDriver);
    wel.getFirst().click();
  }
}
