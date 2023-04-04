package pod.pattern.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PetTest {

    @Test
    @DisplayName("Basic builder creates one instance")
    public void basicBuilder_creates_one_instance() {

        //Given
        Pet.Builder builder = new Pet.Builder();

        //When
        Pet pet = builder.withId(1100).withName("John").withAge(30).build();

        //Then
        assertEquals(1100, pet.getId());
        assertEquals("John", pet.getName());
        assertEquals(30, pet.getAge());

    }

}