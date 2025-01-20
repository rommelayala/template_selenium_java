package com.example.properties;

import com.example.stepDefinitions.LoginSteps;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class EnvironmentProperties implements Config {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EnvironmentProperties.class);
  /** Get properties and set them as system properties */
  public static void initializeProperties() {
    // Desactiva los handlers predeterminados de JUL
    LogManager.getLogManager().reset();

    // Instala el puente de SLF4J
    SLF4JBridgeHandler.install();

    // Prueba el redireccionamiento
    Logger julLogger = Logger.getLogger(EnvironmentProperties.class.getName());
    julLogger.info("Este es un mensaje de java.util.logging redirigido a Logback.");

    org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(EnvironmentProperties.class);
    slf4jLogger.info("Este es un mensaje de SLF4J.");


    String env = System.getProperty("env", "stag");
    ConfigFactory.setProperty("env", env);
    Properties props = new Properties();

    try {
      FileInputStream fis =
          new FileInputStream(new File("src/test/resources/" + env + "_environment.properties"));

      props.load(fis);
      logger.debug("Cargado " + env + "__environment.properties al System");
      for (String key : props.stringPropertyNames()) {
        if (System.getProperty(key) == null) {

          System.setProperty(key, props.getProperty(key));
          logger.debug("Cargado -> key" + key + " : " + props.getProperty(key));

        } else {
          logger.debug(
              "La key : value -> "
                  + key
                  + " : "
                  + props.getProperty(key)
                  + " Ya existe en el System.properties");
        }
      }
      logger.debug("Fin de la carga del " + env + "__environment.properties al System");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String getSystemProperty(String property, String def) {

    return System.getProperty(property,def);
  }
  public String getSystemProperty(String property) {

    return System.getProperty(property);
  }

  public boolean setSystemProperty(String key, String value) {
    if (System.getProperty(key) == null) {
      System.setProperty(key, value);
      logger.debug("Cargado -> key" + key + " : " + value);
      return true;
    } else {
      logger.debug(
          "La variable : valor -> " + key + " : " + value + " Ya existe en el System.properties");
    }
    return false;
  }
}
