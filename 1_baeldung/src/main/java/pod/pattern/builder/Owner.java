package pod.pattern.builder;

import org.inferred.freebuilder.FreeBuilder;

/**
 * References:
 * https://www.baeldung.com/java-builder-pattern-freebuilder
 * https://github.com/inferred/FreeBuilder
 */
@FreeBuilder
public interface Owner {

    long id();

    String name();

    String lastName();

    class Builder extends Owner_Builder {}

}
