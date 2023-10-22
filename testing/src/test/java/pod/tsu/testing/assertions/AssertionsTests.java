package pod.tsu.testing.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pod.tsu.testing.domain.Person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * References:
 * - https://www.baeldung.com/java-testing-single-assert-multiple-properties
 * - https://www.javaguides.net/2018/09/junit-5-assertall-example.html
 */
public class AssertionsTests {

    @Test
    @DisplayName("Test uses assertAll")
    public void assertions_assertAll() {

        // Given
        Person person = Person.builder().name("Inozuke").age(20).build();
        // When Then
        assertAll("Person properties",
            () -> assertEquals("Inozuke", person.getName()),
            () -> assertNull(person.getLastName()),
            () -> assertTrue(person.getAge() > 0)
        );

    }

    @Test
    @DisplayName("Test uses grouped assertAll")
    public void assertions_groupedAssertAll() {

        // Given
        Person person = Person.builder().name("John").lastName("Wick").age(30).build();

        // When Then
        assertAll("Person properties",

            // First group
            () -> {
                assertAll("Basic",
                    () -> assertEquals("John", person.getName()),
                    () -> assertTrue(person.getAge() > 0)
                );
            },

            // Second group
            () -> {
                assertNotNull(person.getLastName());
            }

        );

    }

}