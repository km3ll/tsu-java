package pod.tsu.java.functional.fp_with_java;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Unit")
public class PredicateTest {

    private final Predicate<Integer> isNegative = (x) -> x < 0;

    @Test
    @DisplayName("Run 'test' method")
    void predicate_test() {

        assertFalse(isNegative.test(50));
        assertTrue(isNegative.test(-20));

    }

    @Test
    @DisplayName("Use in a filter function")
    void predicate_inFilter() {

        List<Integer> negatives = Stream.of(10, -20, -30, 40, 50)
            .filter(isNegative)
            .toList();

        negatives.forEach(System.out::println);

    }

    @Test
    @DisplayName("Use with a higher-order function")
    void predicate_hof() {

        Function<Integer, Predicate<String>> factory = (minLength) -> {
            return (str) -> str.length() > minLength;
        };

        Predicate<String> isLongerThan3 = factory.apply(3);
        Predicate<String> isLongerThan10 = factory.apply(10);

        assertTrue(isLongerThan3.test("Hakuna"));
        assertFalse(isLongerThan10.test("Hakuna"));


    }

}
