package org.ecommerce.logs;

public abstract class Log {
    private static final Logger logger = new Logger();

    public static void info(String message) {
        logger.log(message);
    }

    public static void error(String message) {
        logger.log(message);
    }

    public static void warn(String message) {
        logger.log(message);
    }
}
