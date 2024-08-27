package org.ecommerce.logs;

public abstract class Log {
    private static final Logger logger = new Logger();

    protected static void info(String message) {
        logger.log(message);
    }

    protected static void error(String message) {
        logger.log(message);
    }

    protected static void warn(String message) {
        logger.log(message);
    }
}
