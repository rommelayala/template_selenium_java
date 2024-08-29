package com.example.stepDefinitions;

import com.example.pages.HomePage;
import com.example.testscripts.TestBase;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
  private WebDriver driver = TestBase.getDriver();
  private HomePage homePage = new HomePage(driver);
  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {

    homePage.open();
  }
}
