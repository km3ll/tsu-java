package pod.tsu.java.functional.fp_with_java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Tag("Unit")
public class ReduceTest {

    @Test
    @DisplayName("Usage with initial value")
    void reduce_withInitialValue() {

        // Given
        Integer initialValue = 0;
        BinaryOperator<Integer> operator = (acc, element) -> {
            Integer result = acc + element;
            System.out.printf("acc: %d + elem: %d = %d %n", acc, element, result);
            return result;
        };

        // When
        Integer result = Stream.of(5, 10, 15, 20)
            .reduce(initialValue, operator);

        // Then
        assertEquals(50, result);

    }

    @Test
    @DisplayName("Usage without initial value")
    void reduce_withoutInitialValue() {

        // Given
        BinaryOperator<Integer> operator = Integer::sum;

        // When
        Optional<Integer> result = Stream.of(5, 10, 15, 20)
            .reduce(operator);

        // Then
        assertEquals(50, result.orElse(0));

    }

}