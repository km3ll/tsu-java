package pod.tsu.java.testing.parameterized;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("Unit")
public class ParameterizedTests {

    private static Stream<Arguments> provideEndpoints() {
        return Stream.of("caches", "loggers", "metrics").map(Arguments::of);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"health", "info", "shutdown", "startup"})
    @DisplayName("Test uses simple values")
    public void parameterized_simpleValue(String endpoint) {
        assertNotNull(endpoint);
    }

    @ParameterizedTest()
    @MethodSource("provideEndpoints")
    @DisplayName("Test uses a provider method")
    public void parameterized_method(String endpoint) {
        assertNotNull(endpoint);
    }

}