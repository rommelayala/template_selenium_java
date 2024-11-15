package com.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.example.utils.WebDriverSetup;

public class Hooks {
    @Before
    public void beforeScenario() {
        WebDriverSetup.setup(); // Inicializa el WebDriver
    }

    @After
    public void afterScenario() {
        WebDriverSetup.teardown(); // Cierra el WebDriver
    }
}