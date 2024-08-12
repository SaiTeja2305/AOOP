import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Properties properties;

    // Private constructor to prevent instantiation
    private ConfigurationManager() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties file from the classpath
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Static method to get the single instance of ConfigurationManager
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    // Method to get a property by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        String dbHost = configManager.getProperty("db.host");
        String dbPort = configManager.getProperty("db.port");

        System.out.println("Database Host: " + dbHost);
        System.out.println("Database Port: " + dbPort);
    }
}
