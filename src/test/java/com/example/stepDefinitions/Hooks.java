package com.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.example.testScripts.WebDriverSetup;

public class Hooks {
    @Before
    public void beforeScenario() {
        System.out.println("Inicializando el WebDriver desde @Before beforeScenario");
        WebDriverSetup.setup();
    }

    @After
    public void afterScenario() {
        System.out.println("Cerrando el WebDriver desde el @After afterScenario");
        WebDriverSetup.teardown(); // Cierra el WebDriver
    }
}