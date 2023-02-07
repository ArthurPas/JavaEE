package fr.iut.rm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Contains main configuration
 */
public final class Configuration {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    /**
     * Contains configuration properties
     */
    private static Properties properties = new Properties();

    static {
        logger.debug("Loading config.properties files");
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            logger.debug("Configuration loaded");
        } catch (IOException e) {
            logger.error("Unable to load config file", e);
        }
    }

    /**
     * Disable instantiation
     */
    private Configuration() {
    }

    /**
     * @return application version
     */
    public static String getVersion() {
        return properties.getProperty("version");
    }


}
