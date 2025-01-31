package tsu.pod.indexzettels.infra;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tsu.pod.indexzettels.infra.exception.PodException;
import tsu.pod.indexzettels.model.Zettel;

public abstract class FileUtils {

    public static void processFolder(String path) {
        File[] files = FileUtils.readFiles(path);
        for (File file : files) {
            if (file.isDirectory()) {
                processFolder(file.getAbsolutePath());
            } else {
                processFile(file);
            }
        }
    }

    public static File[] readFiles(String path) throws PodException {
        File directory = new File(path);
        if (!directory.exists()) {
            throw new PodException("Path does not exist: " + path);
        }
        if (!directory.isDirectory()) {
            throw new PodException("Path is not a directory: " + path);
        }
        return directory.listFiles();
    }

    public static void processFile(File file) {
        Zettel zettel = Zettel.of(file);
        deleteFile(file);
        writeInFile(file, zettel);
    }

    public static void deleteFile(File file) {
        if (!file.delete()) {
            System.out.println("Error deleting file: " + file.getAbsolutePath());
        }
    }

    public static void writeInFile(File file, Zettel zettel) {
        List<String> lines = zettel.getContent();
        int lastLineIndex = lines.size() - 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                if (i < lastLineIndex) {
                    writer.newLine(); // Add a new line after each entry
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static List<String> getFilePath(File file) {
        String path = getNierPath(file.getAbsolutePath());
        return Arrays.stream(path.split("/"))
            .filter(name -> !StringUtils.isEmpty(name))
            .toList();
    }

    private static String getNierPath(String path) {
        int index = path.indexOf("/nier");
        return path.substring(index);
    }

    public static List<String> readLines(File file) {
        Path path = Paths.get(file.getAbsolutePath());
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            System.out.printf("Error reading lines from file: %s. %s%n", file.getAbsoluteFile(), e.getMessage());
        }
        return lines;
    }

    public static String formatName(String name) {
        if (name.contains(".")) {
            return name.split("\\.")[0].trim();
        } else {
            return name;
        }
    }

    public static List<String> getNamesInDirectory(File file) {
        List<String> fileNames = new ArrayList<>();
        File[] files = readFiles(file.getAbsolutePath());
        Arrays.stream(files)
            .map(raw -> formatName(raw.getName()))
            .filter(formatted -> !formatted.equals(file.getName()))
            .sorted()
            .forEach(fileNames::add);
        return fileNames;
    }

}
