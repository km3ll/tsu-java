package tsu.pod.design_patterns.creational.factory_method.factory;

import tsu.pod.design_patterns.creational.factory_method.button.Button;
import tsu.pod.design_patterns.creational.factory_method.button.HtmlButton;

/**
 * HTML Dialog will produce HTML buttons.
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }

}
