package refactoring_guru.m01_creational.factory_method.example.buttons;

public class ConsoleButton implements Button {

    @Override
    public void render() {
        System.out.println("> [Button]");
    }

    @Override
    public void onClick() {
        System.out.println("> click!");
    }

}