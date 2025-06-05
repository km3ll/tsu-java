package tsu.pod.design_patterns.creational.abstract_factory.factories;

import tsu.pod.design_patterns.creational.abstract_factory.buttons.Button;
import tsu.pod.design_patterns.creational.abstract_factory.buttons.WindowsButton;
import tsu.pod.design_patterns.creational.abstract_factory.checkboxes.Checkbox;
import tsu.pod.design_patterns.creational.abstract_factory.checkboxes.WindowsCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
