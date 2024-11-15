package com.example.stepDefinitions;

import com.example.testSpecs.SeleniumSpec;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginSteps {
  private SeleniumSpec seleniumSpec = new SeleniumSpec();

  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {

    seleniumSpec.gogoUrlUsingProperties();
    // homePage.open();s
  }


  @And("I pause {int} seconds")
  public void iPauseSeconds(int waitSeconds) {
    seleniumSpec.iPauseForWaitSeconds(waitSeconds);
  }
}
