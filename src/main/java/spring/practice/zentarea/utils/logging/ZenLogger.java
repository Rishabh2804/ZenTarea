package spring.practice.zentarea.utils.logging;

import org.apache.logging.log4j.*;

@SuppressWarnings("unused")
public final class ZenLogger {
    private static final Logger logger;

    static {
        logger = LogManager.getLogger(ZenLogger.class);
    }

    private ZenLogger() {
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    public static void log(Level level, Marker marker, String message) {
        logger.log(level, marker, message);
    }

    public static void log(Level level, String message, Throwable throwable) {
        logger.log(level, message, throwable);
    }

    // <--------------- DEBUG -------------------------->
    public static void logD(String message) {
        logger.debug(message);
    }

    public static void logD(Marker marker, String message) {
        logger.debug(marker, message);
    }


    // <--------------------------- INFO -------------------------->
    public static void logI(String message) {
        log(Level.INFO, message);
        logger.info(message);
    }

    public static void logI(Marker marker, String message) {
        log(Level.INFO, message);
        logger.info(marker, message);
    }


    // <-------------------------- WARN -------------------------->
    public static void logW(String message) {
        logger.warn(message);
    }

    public static void logW(Marker marker, String message) {
        logger.warn(marker, message);
    }

    // <-------------------------- ERROR -------------------------->
    public static void logE(String message) {
        logger.error(message);
    }

    public static void logE(Marker marker, String message) {
        logger.error(marker, message);
    }

    // <-------------------------- FATAL -------------------------->
    public static void logF(String message) {
        logger.fatal(message);
    }

    public static void logF(Marker marker, String message) {
        logger.fatal(marker, message);
    }

    // <-------------------------- TRACE -------------------------->
    public static void logT(String message) {
        logger.trace(message);
    }

    public static void logT(Marker marker, String message) {
        logger.trace(marker, message);
    }

    // <-------------------------- ALL -------------------------->
    public static void logA(String message) {
        logger.log(Level.ALL, message);
    }
}
