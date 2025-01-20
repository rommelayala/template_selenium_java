package com.example.stepDefinitions;

import com.example.testSpecs.SeleniumSpec;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;

public class LoginSteps {
  private final SeleniumSpec seleniumSpec = new SeleniumSpec();

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginSteps.class);

  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {

    seleniumSpec.gogoUrlUsingProperties();
  }

  @And("I pause {int} seconds")
  public void iPauseSeconds(int waitSeconds) {
    seleniumSpec.iPauseForWaitSeconds(waitSeconds);
  }
  public void iAmOnTheLoginPage_aspect() {
    logger.debug("Navigating to login page");
  }

  public void enterUsername_aspect(String username) {
    try {
      Thread.sleep(100); // Simulando una operación de entrada de datos
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    logger.info("Entering username: " + username);  }

  public void enterPassword_aspect(String password) {
    try {
      Thread.sleep(150); // Simulando una operación de entrada de datos
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    logger.info("Entering password: " + password);  }

  public void clickLoginButton_aspect() {
    try {
      Thread.sleep(50); // Simulando una operación de clic
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    logger.info("Clicking login button");  }
}
