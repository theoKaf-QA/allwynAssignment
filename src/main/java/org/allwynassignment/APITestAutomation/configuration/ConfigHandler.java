package org.allwynassignment.APITestAutomation.configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ConfigHandler {
    private static ConfigHandler configHandlerInstance;
    private final Properties properties;


    private ConfigHandler() {
        properties = new Properties();
        loadProperties();
    }

    public static ConfigHandler getInstance() {
        if (configHandlerInstance == null) {
            synchronized (ConfigHandler.class) {
                if (configHandlerInstance == null) {
                    configHandlerInstance = new ConfigHandler();
                }
            }
        }
        return configHandlerInstance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            log.error("Unable to load application.properties. Exception message: {}", e.getMessage());
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public String getBaseUri() {
        return getProperty("base.uri");
    }

    public String getBasePath() {
        return getProperty("base.path");
    }

    public String getBooksEndpoint() {
        return getProperty("ep.books");
    }

    public String getAuthorsEndpoint() {
        return getProperty("ep.authors");
    }

    public int getApiTimeout() {
        return Integer.parseInt(getProperty("api.timeout"));
    }

    public int getRetryCount() {
        return Integer.parseInt(getProperty("retry.count"));
    }

    public String getEnvironment() {
        return getProperty("run.environment");
    }

    private String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = properties.getProperty(key);
        }
        if (value == null) {
            log.error("Property key {} not found", key);
            throw new RuntimeException("Property key " + key + " not found.");
        }
        return value;
    }

}
