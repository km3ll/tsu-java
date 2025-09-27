package tsu.pod.baseapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MainTest {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    @Test
    void smoke_test() {

        logger.debug("context loads");
        assertTrue(true);
    }

}