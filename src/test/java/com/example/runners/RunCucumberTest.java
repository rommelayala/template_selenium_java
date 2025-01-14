package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
            "com.example.stepDefinitions"
    },
    plugin = {
      "pretty",
      "html:target/docs/cucumber-report.html",
      "json:target/docs/cucumber.json",
      "rerun:target/docs/rerun.txt",
      "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    })
public class RunCucumberTest extends AbstractTestNGCucumberTests {}
