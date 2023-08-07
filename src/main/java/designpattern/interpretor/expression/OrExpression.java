package designpattern.interpretor.expression;

import designpattern.interpretor.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrExpression implements Expression {

  private List<Expression> expressions = new ArrayList<>();

  public OrExpression(String strOrExpression) {
    String[] andExpressions = strOrExpression.split("\\|\\|");
    for (String andExpression : andExpressions) {
      expressions.add(new AndExpression(andExpression));
    }
  }

  @Override
  public boolean interpret(Map<String, Long> stats) {
    for (Expression expression : expressions) {
      if (expression.interpret(stats)) {
        return true;
      }
    }
    return false;
  }
}
