package org.ecommerce.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
}
