package tsu.pod.indexzettels;

import tsu.pod.indexzettels.infra.FileUtils;

public class Main {
    public static void main(String[] args) {
        FileUtils.processFolder(args[0]);
    }
}