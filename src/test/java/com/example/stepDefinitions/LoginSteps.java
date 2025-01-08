package com.example.stepDefinitions;

import com.example.testSpecs.SeleniumSpec;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {
  private final SeleniumSpec seleniumSpec = new SeleniumSpec();

  @Given("I go to URL using properties file")
  public void iGotoUrlUsingPropertiesFile() {

    seleniumSpec.gotoUrlUsingProperties();
  }

  @Given("I go to this URL {string}")
  public void iGoToThisUrl(String url) {

    seleniumSpec.iGoToThisUrl(url);
  }

  @And("I pause {int} seconds")
  public void iPauseSeconds(int waitSeconds) {
    seleniumSpec.iPauseForWaitSeconds(waitSeconds);
  }

  public void iAmOnTheLoginPage_aspect() {
    System.out.println("Navigating to login page");
  }

  @Given("type {string} on the element with {string}")
  public void type_on_the_element_with(String text, String selector) {

    seleniumSpec.iTypeTextOnInput(text, selector);
  }

  @And("click on the element with {string}")
  public void clickOnTheButtonWithIdBtnLogin(String selector) {
    seleniumSpec.clickOnTheButtonWith( selector);
  }
}
