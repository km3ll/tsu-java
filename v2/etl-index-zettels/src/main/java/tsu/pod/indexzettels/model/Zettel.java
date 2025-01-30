package tsu.pod.indexzettels.model;

import lombok.Getter;
import lombok.Setter;
import tsu.pod.indexzettels.infra.FileUtils;
import tsu.pod.indexzettels.infra.exception.PodException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class Zettel {

    private static final String EMPTY_LINE = "";
    private static final String INDEX_LINE_PREFIX = "- ";
    private static final String LINK_PREFIX = "[[";
    private static final String LINK_POSFIX = "]]";
    private static final String TAG_DELIMITER = " ";
    private static final String TAG_PREFIX = "#";
    private static final String TAG_SEPARATOR = "---";
    private static final String TITTLE_PREFIX = "# ";

    private String name;
    private boolean isIndex;
    private List<String> content;

    public static Zettel of(File file) throws PodException {

        String name = file.getName();
        boolean isIndex = getIsIndex(file);
        List<String> content = new ArrayList<>();
        if (isIndex) {
            content.addAll(buildContentOfIndex(file));
        }

        return new Zettel(name, isIndex, content);

    }

    private static List<String> getTags(File file) {
        return FileUtils.getNameTree(file)
            .stream()
            .filter(name -> !name.endsWith(".md"))
            .map(name -> TAG_PREFIX + name)
            .toList();
    }

    private static boolean getIsIndex(File file) {
        List<String> tree = new ArrayList<>(FileUtils.getNameTree(file));
        Collections.reverse(tree);
        String fileName = formatFileName(tree.get(0));
        String folderName = tree.get(1);
        return folderName.equals(fileName);
    }

    private static String formatFileName(String name) {
        if (name.contains(".")) {
            return name.split("\\.")[0].trim();
        } else {
            return name;
        }
    }

    private static List<String> buildContentOfIndex(File file) {
        List<String> content = new ArrayList<>();
        content.add(TITTLE_PREFIX + formatFileName(file.getName()));
        content.add(EMPTY_LINE);
        for (String fileName : getDirectoryFileNames(file.getParentFile())) {
            content.add(INDEX_LINE_PREFIX + LINK_PREFIX + fileName + LINK_POSFIX);
        }
        content.add(EMPTY_LINE);
        content.add(TAG_SEPARATOR);
        content.add(EMPTY_LINE);
        content.add(String.join(TAG_DELIMITER, getTags(file)));
        return content;
    }

    private static List<String> getDirectoryFileNames(File file) {
        List<String> fileNames = new ArrayList<>();
        Arrays.stream(FileUtils.listFiles(file.getAbsolutePath()))
            .map(raw -> formatFileName(raw.getName()))
            .filter(formatted -> !formatted.equals(file.getName()))
            .forEach(fileNames::add);
        ;
        return fileNames;
    }

    public Zettel(String name, boolean isIndex, List<String> content) {
        this.name = name;
        this.isIndex = isIndex;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Zettel{" +
                "name='" + name + '\'' +
                ", isIndex=" + isIndex +
                ", content=" + content +
                '}';
    }
}