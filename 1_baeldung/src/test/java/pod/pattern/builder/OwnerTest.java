package pod.pattern.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OwnerTest {

    @Test
    @DisplayName("FreeBuilder creates one instance")
    public void freeBuilder_creates_one_instance() {

        //Given
        Owner.Builder builder = new Owner.Builder();

        //When
        Owner owner = builder.id(1100).name("John").lastName("Wick").build();

        //Then
        assertEquals(1100, owner.id());
        assertEquals("John", owner.name());
        assertEquals("Wick", owner.lastName());

    }

}