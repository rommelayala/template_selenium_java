package com.example.properties;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;

public class Environment {

    /**
     * Get properties
     *
     * @return EnvironmentProperties
     */
    public static EnvironmentProperties getProperties() {
        ConfigFactory.setProperty("env", System.getProperty("env", "stag"));
        return ConfigCache.getOrCreate(EnvironmentProperties.class);
    }

}