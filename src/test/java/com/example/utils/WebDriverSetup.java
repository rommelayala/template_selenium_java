package com.example.utils;

import io.testsmith.support.listeners.*;
import com.example.utils.RommonSeleniumUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class WebDriverSetup {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    public static void setup() {
        final String browser = System.getProperty("browser", "firefox");
        WebDriver originalDriver = RommonSeleniumUtil.createDriver(browser);
        driver.set(new EventFiringDecorator(
                new WebDriverLoggingListener(),
                new SavePageSourceOnExceptionListener(originalDriver, "target/log/pagesources"),
                new SaveScreenshotOnExceptionListener(originalDriver, "target/log/screenshots"),
                new HighlightElementsListener()
        ).decorate(originalDriver));
    }

    @AfterClass
    public static void teardown() {
        getDriver().quit();
    }

}
