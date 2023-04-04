package pod.pattern.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Reference:
 * https://www.baeldung.com/creational-design-patterns#1-singleton-pattern-example
 */
class SingletonTest {

    @Test
    @DisplayName("Basic singleton creates one instance")
    public void basicSingleton_creates_one_instance() {

        // Given
        BasicSingleton ref1 = BasicSingleton.getInstance();
        assertEquals("Initial info", ref1.getInfo());

        // When
        BasicSingleton ref2 = BasicSingleton.getInstance();
        ref2.setInfo("New info");

        // Then
        assertEquals("New info", ref1.getInfo());
        assertEquals("New info", ref2.getInfo());

    }

}