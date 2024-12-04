package com.mitrais.atm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {
	private Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        String currentDir = System.getProperty("user.dir");  // Get current working directory
        Path path = Paths.get(currentDir, "src/main/resources", "config.txt");  // Combine it with relative path
        
        try (FileInputStream input = new FileInputStream(path.toString())) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
        }
    }

    // Inner static helper class responsible for holding the Singleton instance
    private static class Holder {
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static ConfigLoader getInstance() {
        return Holder.INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
}
