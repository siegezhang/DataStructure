package designpattern.interpretor.expression;

import designpattern.interpretor.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AndExpression implements Expression {

  private List<Expression> expressions = new ArrayList<>();

  public AndExpression(String strAndExpression) {
    String[] strExpressions = strAndExpression.split("&&");
    for (String strExpression : strExpressions) {
      if (strExpression.contains(">")) {
        expressions.add(new GreaterExpression(strExpression));
      } else if (strExpression.contains("<")) {
        expressions.add(new LesserExpression(strExpression));
      } else if (strAndExpression.contains("==")) {
        expressions.add(new EqualExpression(strExpression));
      } else {
        throw new RuntimeException("Expression is invalid: " + strAndExpression);
      }
    }
  }

  @Override
  public boolean interpret(Map<String, Long> stats) {
    for (Expression expression : expressions) {
      if (!expression.interpret(stats)) {
        return false;
      }
    }
    return true;
  }
}
