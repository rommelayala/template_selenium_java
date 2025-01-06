package com.example.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.example.utils.WebDriverSetup;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.example.properties.EnvironmentProperties.initializeProperties;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void beforeScenario() throws IOException {
        //loadProperties();
        initializeProperties();
        // Inicializa el WebDriver
        driver = WebDriverSetup.setup();
        WebDriverSetup.getInstance().setDriver(driver);
        driver= WebDriverSetup.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException, InterruptedException {

        if (scenario.isFailed()) {
            // take a print screen
            getFailureScreenShot(scenario);
        }
        // driver.quit();

    }

    private void getFailureScreenShot(Scenario scenario) {
        try {
            if (driver instanceof TakesScreenshot screenshot) {
                byte[] screenshot_bytes = screenshot.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot_bytes, "image/png", "Failure Screenshot");
            } else {
                System.err.println("Driver does not support screenshots.");
            }

        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}