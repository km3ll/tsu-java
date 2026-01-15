package tsu.pod.cognos.utils.hash;

import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HmacHasher {

    private static final String HMAC_SHA256 = "HmacSHA256";

    public static String hash(String secret, String value) throws Exception {

        // Secret Key Specification (structured description)
        SecretKeySpec keySpec =
                new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);

        // Message Authentication Code
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(keySpec);

        byte[] rawHmac = mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return new String(rawHmac);
    }

    private HmacHasher() {}
}
