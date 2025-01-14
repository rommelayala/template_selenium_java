package com.example.utils;

import io.qameta.allure.Attachment;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureConfig_OLD implements ITestListener {

    private static final String RESULTS_DIRECTORY = "target/allure-results/";
    private static final WebDriver driver = WebDriverSetup.getDriver();

    @Override
    public void onStart(ITestContext context) {
        // Crear un timestamp para el directorio
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueResultsDirectory = RESULTS_DIRECTORY + "allure-results-" + timestamp;

        // Establecer la variable de sistema para el directorio de resultados
        System.setProperty("allure.results.directory", uniqueResultsDirectory);
    }

    @Override
    public void onFinish(ITestContext context) {
        // Aquí puedes realizar operaciones después de que todas las pruebas hayan finalizado
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(result.getName(), driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] saveScreenshot(String name, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
