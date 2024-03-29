package pod.tsu.java.testing.instancio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.instancio.Random;
import org.instancio.support.DefaultRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Unit")
public class InstancioTests {

    private static final Random random = new DefaultRandom();

    public static String genMethodName() {
        return random.oneOf("GET", "HEAD", "POST", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE", "PATCH");
    }

    @Test
    @DisplayName("Test uses oneOf")
    public void instancio_oneOf() {
        assertNotNull(genMethodName());
    }

}