package lab1.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PropertiesReader {
    private static PropertiesReader ourInstance;
    private static final String PROPERTIES_PATH = "config.properties";

    private Properties properties;
    private Map<String, String> propertiesMap;

    public static synchronized PropertiesReader getInstance() throws IOException {
        if (ourInstance == null) {
            ourInstance = new PropertiesReader();
        }
        return ourInstance;
    }

    private PropertiesReader() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
        properties.load(fis);
        fis.close();

        propertiesMap = new HashMap<>();
        readProperties();
    }

    private void readProperties() {
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = ((String) propertyNames.nextElement());
            String value = properties.getProperty(key);
            propertiesMap.put(key, value);
        }
    }

    public String getPropertyValue(String key) {
        return propertiesMap.get(key);
    }

    public String getAllValues() {
        return "Properties" + propertiesMap.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertiesReader that = (PropertiesReader) o;
        return properties.equals(that.properties) &&
                propertiesMap.equals(that.propertiesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties, propertiesMap);
    }
}
