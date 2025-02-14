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
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsu.pod.indexzettels.Main;
import tsu.pod.indexzettels.infra.exception.PodException;
import tsu.pod.indexzettels.model.Zettel;

public abstract class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final List<String> ignoredFileNames = List.of(".ds_store");

    public static void processFolder(String path) throws Exception {
        List<File> files = FileUtils.readFiles(path);
        for (File file : files) {
            if (file.isDirectory()) {
                logger.debug("Directory: {}", file);
                processFolder(file.getAbsolutePath());
            } else {
                logger.debug("File: {}", file);
                processFile(file);
            }
        }
    }

    public static List<File> readFiles(String path) throws PodException {
        List<File> fileList = new ArrayList<>();
        File directory = new File(path);
        if (!directory.exists()) {
            throw new PodException("Path does not exist: " + path);
        }
        if (!directory.isDirectory()) {
            throw new PodException("Path is not a directory: " + path);
        }
        File[] files = directory.listFiles();
        if (files != null) {
            fileList.addAll(Arrays.asList(files));
        }
        return fileList.stream()
            .filter(fileIsAllowed)
            .toList();
    }

    public static void processFile(File file) throws Exception {
        logger.debug("File size: {} bytes, path: : {}", file.length(), file.getAbsolutePath());
        File backup = createBackup(file);
        Zettel zettel = Zettel.of(file);
        saveFile(file, zettel);
        long backupSize = backup.length();
        long updatedSize = file.length();
        if (updatedSize >= backupSize) {
            deleteBackup(backup);
        } else {
            logger.error("Updated size: {} is lower than original size: {}. Restoring backup from file: {}", updatedSize, backupSize, backup.getAbsoluteFile());
            //restoreBackup(backup);
        }
    }

    private static File createBackup(File file) throws Exception{
        String path = file.getAbsolutePath().replace(".md", "_backup.md");
        File backup = new File(path);
        org.apache.commons.io.FileUtils.copyFile(file, backup);
        return backup;
    }

    public static void saveFile(File file, Zettel zettel) throws Exception {
        deleteFile(file);
        writeInFile(file, zettel);
    }

    public static void deleteFile(File file) throws Exception {
        if (!file.delete()) {
            throw new Exception("Error deleting file: " + file.getAbsolutePath());
        }
    }

    public static void writeInFile(File file, Zettel zettel) throws Exception {
        List<String> lines = zettel.getContent();
        int lastLineIndex = lines.size() - 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get(i));
                if (i < lastLineIndex) {
                    writer.newLine(); // Add a new line after each entry
                }
            }
        } catch (IOException ex) {
            logger.error("Error writing file: {}", file.getAbsoluteFile(), ex);
            throw new Exception(ex);
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
        List<File> files = readFiles(file.getAbsolutePath());
        files.stream()
            .filter(unformatted -> !unformatted.getName().contains("_backup"))
            .map(raw -> formatName(raw.getName()))
            .filter(formatted -> !formatted.equals(file.getName()))
            .sorted()
            .forEach(fileNames::add);
        return fileNames;
    }

    private static final Predicate<File> fileIsAllowed = file -> {
        return !ignoredFileNames.contains(file.getName().toLowerCase());
    };

    private static void deleteBackup(File file) throws Exception {
        deleteFile(file);
    }

    private static void restoreBackup(File backup) throws Exception{
        String path = backup.getAbsolutePath().replace("_backup.md", ".md");
        File restored = new File(path);
        deleteFile(restored);
        org.apache.commons.io.FileUtils.copyFile(backup, restored);
        deleteFile(backup);
    }

}