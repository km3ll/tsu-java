package tsu.pod.cognos.core.model;

import java.util.Arrays;
import java.util.List;

public enum CountryCode {
  CAN("CAN"),
  USA("USA");

  private final String value;

  CountryCode(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static final List<String> CODES =
      Arrays.stream(values()).map(CountryCode::getValue).sorted().toList();

  public static CountryCode from(String value) {
    return Arrays.stream(values())
        .filter(code -> code.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(
            () -> {
              String msg = "Invalid country code. Expected one of %s. Found %s";
              return new IllegalArgumentException(String.format(msg, CODES, value));
            });
  }
}
