package tsu.pod.indexzettels;

import tsu.pod.indexzettels.infra.FileUtils;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: missing folder-path argument");
            System.out.println("Usage: java -jar etl-index-zettels-1.0.jar <folder-path>");
            System.exit(1);
        }
        FileUtils.processFolder(args[0]);
    }
}