package designpattern.interpretor;

import designpattern.interpretor.expression.AbstractExpression;

public class TerminalExpression implements AbstractExpression {

  @Override
  public void interpret(Context context) {
    System.out.println("对终结符表达式进行处理");
  }
}
