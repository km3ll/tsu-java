package tsu.pod.design_patterns.creational.factory_method.factory;

import tsu.pod.design_patterns.creational.factory_method.button.Button;
import tsu.pod.design_patterns.creational.factory_method.button.WindowsButton;

/**
 * EN: Windows Dialog will produce Windows buttons.
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}