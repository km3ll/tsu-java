package tsu.pod.indexzettels;

import tsu.pod.indexzettels.infra.FileUtils;
import tsu.pod.indexzettels.model.Zettel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        var path = "nier";
        index(path);
    }

    private static void index(String path) {
        File[] files = FileUtils.listFiles(path);
        for (File file : files) {
            if (file.isDirectory()) {
                index(file.getAbsolutePath());
            } else {
                Zettel zettel = Zettel.of(file);
                if (zettel.isIndex()) {
                    file.delete();
                }
                System.out.println(zettel);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    var lines = zettel.getContent();
                    for (int i = 0; i < lines.size(); i++) {
                        writer.write(lines.get(i));
                        if (i < lines.size()-1) {
                            writer.newLine(); // Add a new line after each entry
                        }

                    }
                    //System.out.println("File created successfully: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Error writing file: " + e.getMessage());
                }
            }
        }
    }

}