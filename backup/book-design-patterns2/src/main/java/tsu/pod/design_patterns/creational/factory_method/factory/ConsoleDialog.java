package tsu.pod.design_patterns.creational.factory_method.factory;

import tsu.pod.design_patterns.creational.factory_method.button.Button;
import tsu.pod.design_patterns.creational.factory_method.button.ConsoleButton;

public class ConsoleDialog extends Dialog {

    @Override
    public Button createButton() {
        return new ConsoleButton();
    }

}
