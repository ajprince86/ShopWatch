package com.shopwatch.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    // Properties object holds key-value pairs from our file
    private static Properties properties = new Properties();

    // Static block - loads the file once when class is loaded
    static {
        try {
            FileInputStream file = new FileInputStream(
                    "src/test/resources/config.properties"
            );
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("config.properties file not found", e);
        }
    }

    // Get a value by key
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Get a value as an integer
    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}