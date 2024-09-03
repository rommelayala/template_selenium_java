package com.example.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AllureConfig implements ITestListener {

    private static final String RESULTS_DIRECTORY = "target/allure-results/";

    @Override
    public void onStart(ITestContext context) {
        // Crear un timestamp para el directorio
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueResultsDirectory = RESULTS_DIRECTORY + "allure-results-" + timestamp;
    System.out.println("HOLA desde AllureConfig");

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
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
