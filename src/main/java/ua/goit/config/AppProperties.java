package ua.goit.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static AppProperties value;
    private Properties properties;

    private AppProperties() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        if (value == null) {
            value = new AppProperties();
        }
        return value.properties;
    }
}
