package tsu.pod.design_patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsu.pod.design_patterns.creational.factory_method.factory.ConsoleDialog;
import tsu.pod.design_patterns.creational.factory_method.factory.Dialog;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("----------");
        logger.info("Creational Patterns");
        runFactoryMethod();
    }

    private static void runFactoryMethod() {
        logger.info(" > Factory Method");
        Dialog dialog = new ConsoleDialog();
        dialog.renderWindow();
    }

}