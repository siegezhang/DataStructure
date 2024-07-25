package annotation.desensitized.another2;

import java.util.function.Function;

public enum SensitiveStrategy {
  ID_CARD(s -> s.replaceAll("(\\d{3})\\d{12}(\\w{3})", "$1*****$2")),
  PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),
  ADDRESS(s -> s.replaceAll("(\\S{3})\\S{2}\\S*(\\S{2})", "$1********$2"));

  private final Function<String, String> serialize;

  SensitiveStrategy(Function<String, String> serialize) {
    this.serialize = serialize;
  }

  public Function<String, String> serialize() {
    return serialize;
  }
}
