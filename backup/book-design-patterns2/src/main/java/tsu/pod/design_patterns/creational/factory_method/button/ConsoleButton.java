package tsu.pod.design_patterns.creational.factory_method.button;

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