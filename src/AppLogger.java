
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class AppLogger {

    private static final Logger LOGGER = Logger.getLogger("TaxiRoute");
    static {
        try {
            new File("data").mkdirs();  
            FileHandler handler = new FileHandler("data/app.log", true);
            
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date(record.getMillis()));
                    return time + " [" + record.getLevel() + "] "
                            + record.getMessage() + System.lineSeparator();
                }
            });

            LOGGER.addHandler(handler);
            LOGGER.setUseParentHandlers(true);   // stops log lines appearing in the console
        } catch (IOException e) {
            System.out.println("Logging unavailable: " + e.getMessage());
        }
    }

   
    public static void info(String message) {
        LOGGER.info(message);
    }

    // things that went wrong but did not stop the program
    public static void warning(String message) {
        LOGGER.warning(message);
    }

    // serious failures
    public static void severe(String message) {
        LOGGER.severe(message);
    }
}
