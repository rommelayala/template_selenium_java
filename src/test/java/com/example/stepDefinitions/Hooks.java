package com.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.example.utils.WebDriverSetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.example.properties.EnvironmentProperties.initializeProperties;

public class Hooks {

    @Before
    public void beforeScenario() throws IOException {
        //loadProperties();
        initializeProperties();
        // Inicializa el WebDriver
        WebDriverSetup.setup();
    }

    @After
    public void afterScenario() {
        WebDriverSetup.teardown(); // Cierra el WebDriver
    }
}