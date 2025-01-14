package com.example.utils;

import java.util.Properties;

public final class ThreadProperty {
    private static final ThreadLocal<Properties> PROPS = new ThreadLocal<Properties>() {
        protected Properties initialValue() {
            return new Properties();
        }
    };

    /**
     * Default Constructor.
     */
    private ThreadProperty() {
    }

    /**
     * Set a string to share in other class.
     *
     * @param key   the key
     * @param value the value
     */
    public static void set(String key, String value) {
        if (value != null) {
            PROPS.get().setProperty(key, value);
        }
    }

    /**
     * Get a property shared.
     *
     * @param key the key
     * @return String string
     */
    public static String get(String key) {
        return PROPS.get().getProperty(key);
    }

    /**
     * Get a property shared. If not found it returns the default value.
     *
     * @param key           the key
     * @param defaultValue  The default value
     * @return String       string
     */
    public static String get(String key, String defaultValue) {
        return PROPS.get().getProperty(key, defaultValue);
    }

}