package tsu.pod.indexzettels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsu.pod.indexzettels.infra.FileUtils;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: missing folder-path argument");
            System.out.println("Usage: java -jar etl-index-zettels-1.0.jar <folder-path>");
            System.exit(1);
        }

        logger.info("Processing folder: {}", args[0]);
        try {
            FileUtils.processFolder(args[0]);
        } catch (Exception ex) {
            logger.error("Could not process folder", ex);
        }
        logger.info("Complete");

    }


}