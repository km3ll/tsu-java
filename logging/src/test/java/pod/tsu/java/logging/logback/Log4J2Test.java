package pod.tsu.java.logging.logback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Unit")
public class Log4J2Test {

    private final Logger logger = LogManager.getLogger(Log4J2Test.class);

    @Test
    @DisplayName("Log4J2 logger writes messages")
    public void logger_logsMessages() {

        logger.info("Info message");
        logger.debug("Debug message");

    }


}
