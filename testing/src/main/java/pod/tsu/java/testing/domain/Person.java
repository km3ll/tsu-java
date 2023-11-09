package pod.tsu.java.testing.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Person {

    private String name;
    private String lastName;
    private int age;

}