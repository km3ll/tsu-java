package refactoring_guru.m01_creational.factory_method.example.factory;

import refactoring_guru.m01_creational.factory_method.example.buttons.Button;
import refactoring_guru.m01_creational.factory_method.example.buttons.ConsoleButton;

public class ConsoleDialog extends Dialog {

    @Override
    public Button createButton() {
        return new ConsoleButton();
    }

}
