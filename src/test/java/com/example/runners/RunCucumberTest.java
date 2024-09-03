package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
            "com.example.stepDefinitions",
            "com.example.Hooks"
    },
    plugin = {
      "pretty",
      "html:target/cucumber-reports.html",
      "json:target/cucumber-reports/cucumber.json",
      "rerun:target/cucumber-reports/rerun.txt",
      "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    })
public class RunCucumberTest extends AbstractTestNGCucumberTests {}
