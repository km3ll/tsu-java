package tsu.pod.indexzettels.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import tsu.pod.indexzettels.infra.FileUtils;
import tsu.pod.indexzettels.infra.exception.PodException;

@Setter
@Getter
public class Zettel {

    private static final String DASH = "-";
    private static final String EMPTY_LINE = "";
    private static final String INDEX_LINE_PREFIX = "- ";
    private static final String LINK_PREFIX = "[[";
    private static final String LINK_POSFIX = "]]";
    private static final String SPACE = " ";
    private static final String TAG_DELIMITER = " ";
    private static final String TAG_PREFIX = "#";
    private static final String TAG_SEPARATOR = "--- ";
    private static final String TITTLE_PREFIX = "# ";

    private String name;
    private boolean isIndex;
    private List<String> content;

    private Zettel(String name, boolean isIndex, List<String> content) {
        this.name = name;
        this.isIndex = isIndex;
        this.content = content;
    }

    private Zettel() {
    }

    public static Zettel of(File file) throws PodException {
        String name = file.getName();
        boolean isIndex = zettelIsIndex(file);
        List<String> content = new ArrayList<>();
        if (isIndex) {
            content.addAll(getIndexContent(file));
        } else {
            content.addAll(getNoteContent(file));
        }
        return new Zettel(name, isIndex, content);
    }

    private static boolean zettelIsIndex(File file) {
        List<String> path = new ArrayList<>(FileUtils.getFilePath(file));
        Collections.reverse(path);
        String fileName = FileUtils.formatName(path.get(0));
        String parentFolderName = path.get(1);
        return parentFolderName.equals(fileName);
    }

    private static List<String> getIndexContent(File file) {
        List<String> content = new ArrayList<>();
        content.add(TITTLE_PREFIX + FileUtils.formatName(file.getName()));
        content.add(EMPTY_LINE);

        List<String> fileNames = FileUtils.getNamesInDirectory(file.getParentFile());
        for (String fileName : fileNames) {
            content.add(INDEX_LINE_PREFIX + LINK_PREFIX + fileName + LINK_POSFIX);
        }

        content.add(EMPTY_LINE);
        content.add(TAG_SEPARATOR);
        content.add(EMPTY_LINE);
        content.add(String.join(TAG_DELIMITER, getTags(file)));
        return content;
    }

    private static List<String> getNoteContent(File file) {
        List<String> content = new ArrayList<>();
        List<String> lines = FileUtils.readLines(file);
        for (int i = 0; i < lines.size(); i++) {
            // Ignore line corresponding to tag separator and subsequent lines.
            if (lines.get(i).startsWith(TAG_SEPARATOR)) {
                // Add an empty line as last line when previous line of tag separator was not empty
                if (!StringUtils.isEmpty(lines.get(i-1))) {
                    content.add(EMPTY_LINE);
                }
                break;
            }
            content.add(lines.get(i));
            // Ensure there is an empty line as last line
            if (i == lines.size() - 1) {
                if (!StringUtils.isEmpty(lines.get(i))) {
                    content.add(EMPTY_LINE);
                }
            }
        }
        content.add(TAG_SEPARATOR);
        content.add(EMPTY_LINE);
        content.add(String.join(TAG_DELIMITER, getTags(file)));
        return content;
    }

    private static List<String> getTags(File file) {
        return FileUtils.getFilePath(file)
            .stream()
            .filter(name -> !name.endsWith(".md"))
            .map(Zettel::formatTagName)
            .toList();
    }

    private static String formatTagName(String name) {
        String tagName = name.toLowerCase().replaceAll(SPACE, DASH);
        return TAG_PREFIX + tagName;
    }

    @Override
    public String toString() {
        return "Zettel {" +
            "name='" + name + '\'' +
            ", isIndex=" + isIndex +
            ", content=" + content +
            '}';
    }

}