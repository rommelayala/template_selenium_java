package com.example.testSpecs;

import com.example.properties.EnvironmentProperties;
import com.example.utils.RommonSeleniumUtil;
import com.example.utils.Wait;
import com.example.utils.WebDriverSetup;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class SeleniumSpec {
  private final WebDriver webDriver;
  private final Wait wait;
  private final RommonSeleniumUtil rSeleniumUtil = new RommonSeleniumUtil();
  private static final EnvironmentProperties environmentProperties = new EnvironmentProperties();

  public SeleniumSpec() {
    this.webDriver = WebDriverSetup.getInstance().getDriver();
    this.wait = new Wait(webDriver);
  }

  /**
   * Navega a una URL configurada en las propiedades.
   */
  public void goToUrlUsingProperties() {
    String url = environmentProperties.getSystemProperty("url", "http://default.url");
    webDriver.get(url);
  }

  /**
   * Navega a una URL especificada.
   */
  public void goToUrl(String url) {
    webDriver.get(url);
  }

  /**
   * Configura un tiempo de espera implícito.
   */
  public void setImplicitWait(int waitSeconds) {
    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
  }

  /**
   * Escribe texto en un campo de entrada identificado por un selector.
   */
  public void typeText(String text, String selector) {
    String[] methodSelector = selector.split(":");
    wait.force_sleep(500);

    List<WebElement> elements = rSeleniumUtil.getWebElementBy(methodSelector[0], methodSelector[1], webDriver);

    Optional<WebElement> element = elements.stream().findFirst();
    element.ifPresentOrElse(
            e -> e.sendKeys(text),
            () -> {
              throw new WebDriverException("No elements found for selector: " + selector);
            }
    );
  }

  /**
   * Hace clic en un botón identificado por un selector.
   */
  public void clickButton(String selector) {
    String[] methodSelector = selector.split(":");

    try {
      List<WebElement> elements = rSeleniumUtil.getWebElementBy(methodSelector[0], methodSelector[1], webDriver);

      WebElement element = elements.stream().findFirst().orElseThrow(() ->
              new NoSuchElementException("No elements found for selector: " + selector)
      );
      element.click();

    } catch (NoSuchElementException e) {
      throw new WebDriverException("Element not found or not clickable: " + selector, e);
    }
  }
}
