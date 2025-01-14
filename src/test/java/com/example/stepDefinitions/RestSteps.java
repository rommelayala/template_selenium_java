package com.example.stepDefinitions;

import com.example.testSpecs.RestSpec;
import com.example.testSpecs.SeleniumSpec;
import com.example.utils.RommonRestUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestSteps {
  private final RestSpec restSpec = new RestSpec();
    RommonRestUtil request;

    @Given("I prepare {string} request to {string}")
  public void iPrepareRequestTo(String requestType, String url){
      request = restSpec.generateRestRequest(requestType);

    }


  @When("I send the request")
  public void iSendTheRequest() {
  }

  @Then("the response body is {string}")
  public void theResponseBodyIs(String arg0) {
    System.out.println("--->>> the response body is "+arg0);

  }

  @And("the response body contains parameter {string}")
  public void theResponseBodyContainsParameterLocationUrl(String arg0) {
    System.out.println("--->>> the response body contains paramete "+arg0);
  }

  @And("the response body parameter {string} has exactly value {string}")
  public void theResponseBodyParameterLocationUrlHasExactlyValueHttpsRickandmortyapiComApiLocation(int arg0) {
  }

  @And("the response body parameter {string} contains value {string}")
  public void theResponseBodyParameterLocationUrlContainsValueApiLocation(int arg0) {
  }

  @And("the response body parameter {string} type is {string}")
  public void theResponseBodyParameterCreatedTypeIsDate() {
  }

  @And("the response body schema matches with {string}")
  public void theResponseBodySchemaMatchesWithSchemasCharacterSchemaJson() {
  }
}
