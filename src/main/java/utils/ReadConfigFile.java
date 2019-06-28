package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile {

    // Read Config
    public Properties properties;

    public ReadConfigFile() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Constant.CONFIG_PROPERTIES_DIRECTORY));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + Constant.CONFIG_PROPERTIES_DIRECTORY);
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) return browser;
        else throw new RuntimeException("Browser not specified in the Configuration.properties file.");
    }

    public String getUrl() {
        String url = properties.getProperty("URL");
        if (url != null) return url;
        else throw new RuntimeException("URL not specified in the Configuration.properties file.");
    }

    public String getTestDataResourcePath() {
        String testDataResourcePath = properties.getProperty("testDataResourcePath");
        if (testDataResourcePath != null) return testDataResourcePath;
        else throw new RuntimeException("testDataResourcePath not specified in the Configuration.properties file.");
    }

    public String getResourceName() {
        String resourceName = properties.getProperty("resourceName");
        if (resourceName != null) return resourceName;
        else throw new RuntimeException("testDataResourcePath not specified in the Configuration.properties file.");
    }

    public String getUserName() {
        String resourceName = properties.getProperty("username");
        if (resourceName != null) return resourceName;
        else throw new RuntimeException("username not specified in the Configuration.properties file.");
    }

    public String getPassword() {
        String resourceName = properties.getProperty("password");
        if (resourceName != null) return resourceName;
        else throw new RuntimeException("password not specified in the Configuration.properties file.");
    }
}
