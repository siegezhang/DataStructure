package designpattern.interpretor;

import designpattern.interpretor.expression.OrExpression;

import java.util.Map;

public class AlertRuleInterpreter {

  private Expression expression;

  public AlertRuleInterpreter(String ruleExpression) {
    this.expression = new OrExpression(ruleExpression);
  }

  public boolean interpret(Map<String, Long> stats) {
    return expression.interpret(stats);
  }
}
