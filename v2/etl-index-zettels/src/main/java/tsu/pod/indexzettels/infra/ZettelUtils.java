package tsu.pod.indexzettels.infra;

import org.apache.commons.lang3.StringUtils;

public abstract class ZettelUtils {

    public static boolean isZettel(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        return path.trim().toLowerCase().endsWith(".md");
    }

}
