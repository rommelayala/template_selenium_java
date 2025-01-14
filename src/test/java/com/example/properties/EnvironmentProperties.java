package com.example.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentProperties implements Config {

  /** Get properties and set them as system properties */
  public static void initializeProperties() {
    String env = System.getProperty("env", "stag");
    ConfigFactory.setProperty("env", env);
    Properties props = new Properties();

    try {
      FileInputStream fis =
          new FileInputStream(new File("src/test/resources/" + env + "_environment.properties"));

      props.load(fis);
      System.out.println("Cargado " + env + "__environment.properties al System");
      for (String key : props.stringPropertyNames()) {
        if (System.getProperty(key) == null) {

          System.setProperty(key, props.getProperty(key));
          System.out.println("Cargado -> key" + key + " : " + props.getProperty(key));

        } else {
          System.out.println(
              "La key : value -> "
                  + key
                  + " : "
                  + props.getProperty(key)
                  + " Ya existe en el System.properties");
        }
      }
      System.out.println("Fin de la carga del " + env + "__environment.properties al System");
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
      System.out.println("Cargado -> key" + key + " : " + value);
      return true;
    } else {
      System.out.println(
          "La variable : valor -> " + key + " : " + value + " Ya existe en el System.properties");
    }
    return false;
  }
}
