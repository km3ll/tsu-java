package pod.tsu.java.logging.logback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag("Unit")
public class Slf4jTest {

    private final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    @DisplayName("SLF4J logger writes messages")
    public void logger_logsMessages() {

        logger.info("Message");
        logger.debug("Message");

    }

}