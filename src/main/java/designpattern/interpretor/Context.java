package designpattern.interpretor;

import java.util.HashMap;
import java.util.Map;

public class Context {
  private Map<String, String> contextMap = new HashMap<>();

  public void assign(String key, String value) {
    // 向上下文Map中设置值
    contextMap.put(key, value);
  }

  public String lookup(String key) {
    return contextMap.get(key);
  }
}
