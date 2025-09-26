package tsu.pod.design_patterns.creational.abstract_factory.factories;

import tsu.pod.design_patterns.creational.abstract_factory.buttons.Button;
import tsu.pod.design_patterns.creational.abstract_factory.checkboxes.Checkbox;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
