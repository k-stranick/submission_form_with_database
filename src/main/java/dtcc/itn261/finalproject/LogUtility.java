/**==================================================
 Author: Kyle Stranick
 Class: ITN261
 Class Section: 201
 Assignment: Final Project
 Notes: there are some cool features need to go over and update this summer
 use as project piece for resume
 4/23/24
 =====================================================

 Code adapted from: check resources

 ===================================================== */
package dtcc.itn261.finalproject;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A utility class for logging information and errors.
 */
public class LogUtility {
    private static final Logger logger = Logger.getLogger(LogUtility.class.getName());
    private static final String LOG_FILE_NAME = "app.log";  // Default log file name

    // This method is called automatically when the class is loaded.
    static { // static initialization block
        setUpLogger();
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private LogUtility() {
    }

    /**
     * Initializes the logger and sets up the file handler for logging.
     * Throws IOException if an error occurs while initializing the log file handler
     */
    private static void setUpLogger() {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            ExceptionAlert.showIOAlert();
            logger.log(Level.SEVERE, "Failed to initialize log file handler.", e);
        }
    }

    public static void info(String message) {
        logger.log(Level.INFO, message);
    }

    public static void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    public static void error(String message, Throwable e) {
        logger.log(Level.SEVERE, message, e);
    }
}

