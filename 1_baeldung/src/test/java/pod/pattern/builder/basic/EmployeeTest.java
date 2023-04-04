package pod.pattern.builder.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    @DisplayName("Basic builder creates one instance")
    public void basicBuilder_creates_one_instance() {

        //Given
        Employee.Builder builder = new Employee.Builder();

        //When
        Employee employee = builder.withId(1100).withName("John").withAge(30).build();

        //Then
        assertEquals(1100, employee.getId());
        assertEquals("John", employee.getName());
        assertEquals(30, employee.getAge());

    }

}