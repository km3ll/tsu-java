package tsu.pod.cognos.utils.codec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Base64Codec {

    public static String encode(String plainText) {
        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decode(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private Base64Codec() {}
}
