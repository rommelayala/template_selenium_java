package com.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.example.testscripts.TestBase;

public class Hooks {
    @Before
    public void beforeScenario() {
        TestBase.setup(); // Inicializa el WebDriver
    }

    @After
    public void afterScenario() {
        TestBase.teardown(); // Cierra el WebDriver
    }
}