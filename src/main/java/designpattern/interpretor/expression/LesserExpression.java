package designpattern.interpretor.expression;

import designpattern.interpretor.Expression;
import java.util.Map;

public class LesserExpression implements Expression {

  private String key;
  private long value;

  public LesserExpression(String strExpression) {
    String[] elements = strExpression.trim().split("\\s+");
    if (elements.length != 3 || !elements[1].trim().equals("<")) {
      throw new RuntimeException("Expression is invalid: " + strExpression);
    }
    this.key = elements[0].trim();
    this.value = Long.parseLong(elements[2].trim());
  }

  @Override
  public boolean interpret(Map<String, Long> stats) {
    if (!stats.containsKey(key)) {
      return false;
    }
    Long statsValue = stats.get(key);
    return statsValue < value;
  }
}
