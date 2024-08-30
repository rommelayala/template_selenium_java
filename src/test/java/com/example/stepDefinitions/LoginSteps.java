package com.example.stepDefinitions;

import com.example.testScripts.TestBase;
import com.example.testScripts.SeleniumSpec;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
  private SeleniumSpec seleniumSpec = new SeleniumSpec();

  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {

    seleniumSpec.openBrowserUsingProperties();
    // homePage.open();s
  }
}
