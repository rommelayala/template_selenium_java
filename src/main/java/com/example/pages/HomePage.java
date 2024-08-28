package com.example.pages;

import com.example.properties.Environment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPageBase {

    private final By signInButton = By.cssSelector("li>a[data-test='nav-sign-in']");
    // getDriver().findElement(By.cssSelector("li>a[data-test='nav-sign-in]'"))

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Practice Software Testing homepage")
    public HomePage open() {
        driver.get(Environment.getProperties().url());
        return this;
    }

    @Step("Is Sign In button displayed")
    public boolean isSignInButtonDisplayed() {
        return driver.findElement(signInButton).isDisplayed();
    }

}
