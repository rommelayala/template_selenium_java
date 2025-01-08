package com.example.listeners;

import static io.testsmith.support.listeners.FileUtil.MAX_FILENAME;

import io.testsmith.support.listeners.SavePageSourceOnExceptionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.openqa.selenium.WebDriver;

public class SavepageSourceOnExceptionListener extends SavePageSourceOnExceptionListener {
    private final String folder;
    private final WebDriver driver;

    public SavepageSourceOnExceptionListener(WebDriver driver, String folder) {
        super(driver, folder);
        this.driver= driver;
        this.folder = folder;
        System.out.println("SavepageSourceOnExceptionListener--> Registrado");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {

        String htmlFilename = generateRandomFilename(e.getTargetException().getMessage().toLowerCase(Locale.ROOT)).concat(".html");
        String errorMessageFilename = generateRandomFilename(e.getTargetException().getMessage().toLowerCase(Locale.ROOT)).concat(".txt");

        saveFile(folder, htmlFilename, driver.getPageSource().getBytes());
        saveFile(folder, errorMessageFilename, e.getTargetException().getMessage().getBytes());
    }
    private static String generateRandomFilename(String filename) {
        if (filename.length() >= MAX_FILENAME) {
            filename = filename.substring(0, MAX_FILENAME);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSS");
        filename = filename.replaceAll("\\s", "_").replaceAll(":", "");
        filename = filename.replaceAll("\"",  "");
        return dateFormat.format(new Date()) +
                "-" + filename;
    }
    private static void saveFile(String folder, String filename, byte[] data) {
        try {
            File file = new File(folder, filename);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream os = new FileOutputStream(file);
            os.write(data);
            os.close();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save file", e);
        }
    }
}
