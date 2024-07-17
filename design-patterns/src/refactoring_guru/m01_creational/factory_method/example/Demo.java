package refactoring_guru.m01_creational.factory_method.example;

import refactoring_guru.m01_creational.factory_method.example.factory.Dialog;
import refactoring_guru.m01_creational.factory_method.example.factory.HtmlDialog;
import refactoring_guru.m01_creational.factory_method.example.factory.WindowsDialog;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {

    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * The concrete factory is usually chosen depending on configuration or
     * environment options.
     */
    static void configure() {
        String osName = System.getProperty("os.name");
        System.out.println("OS Name: " + osName);
        if (osName.equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * All of the client code should work with factories and products through
     * abstract interfaces. This way it does not care which factory it works
     * with and what kind of product it returns.
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }

}