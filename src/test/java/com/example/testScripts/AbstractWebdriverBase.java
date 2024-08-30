package com.example.testScripts;

import java.time.Duration;
import org.openqa.selenium.WebDriver;

abstract class AbstractWebdriverBase {
    protected WebDriver driver;

    public AbstractWebdriverBase(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
}
