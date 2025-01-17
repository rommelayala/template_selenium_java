package com.example.stepDefinitions;

import com.example.testSpecs.SeleniumSpec;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {
  private final SeleniumSpec seleniumSpec = new SeleniumSpec();

  @Given("I go to URL using properties file")
  public void iGoToUrlUsingPropertiesFile() {
    try {
      seleniumSpec.goToUrlUsingProperties();
    } catch (Exception e) {
      throw new RuntimeException("Failed to navigate to URL from properties file.", e);
    }
  }

  @Given("I go to this URL {string}")
  public void iGoToThisUrl(String url) {
    try {
      seleniumSpec.goToUrl(url);
    } catch (Exception e) {
      throw new RuntimeException("Failed to navigate to the specified URL: " + url, e);
    }
  }

  @And("I pause {int} seconds")
  public void iPauseSeconds(int waitSeconds) {
    try {
      seleniumSpec.setImplicitWait(waitSeconds);
    } catch (Exception e) {
      throw new RuntimeException("Failed to pause execution for " + waitSeconds + " seconds.", e);
    }
  }

  @Given("type {string} on the element with {string}")
  public void typeOnTheElementWith(String text, String selector) {
    try {
      seleniumSpec.typeText(text, selector);
    } catch (Exception e) {
      throw new RuntimeException("Failed to type text: '" + text + "' on element with selector: " + selector, e);
    }
  }

  @And("click on the element with {string}")
  public void clickOnTheButtonWith(String selector) {
    try {
      seleniumSpec.clickButton(selector);
    } catch (Exception e) {
      throw new RuntimeException("Failed to click on the element with selector: " + selector, e);
    }
  }
}
