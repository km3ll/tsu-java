package tsu.pod.indexzettels.infra;

import org.apache.commons.lang3.StringUtils;
import tsu.pod.indexzettels.infra.exception.PodException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FileUtils {

    public static File[] listFiles(String path) throws PodException {
        File directory = new File(path);
        if (!directory.exists()) {
            throw new PodException("Path does not exist: " + path);
        }
        if (!directory.isDirectory()) {
            throw new PodException("Path is not a directory: " + path);
        }
        return directory.listFiles();
    }

    public static List<String> getNameTree(File file) {
        String path = getPathFromNier(file.getAbsolutePath());
        return Arrays.stream(path.split("/"))
            .filter(name -> !StringUtils.isEmpty(name))
            .toList();
    }

    private static String getPathFromNier(String path) {
        int index = path.indexOf("/nier");
        return path.substring(index);
    }

    public static List<String> readLines(File file) {
        Path path = Paths.get(file.getAbsolutePath()); // Specify your file path
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}
