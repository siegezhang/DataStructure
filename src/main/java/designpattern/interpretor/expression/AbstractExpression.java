package designpattern.interpretor.expression;

import designpattern.interpretor.Context;

public interface AbstractExpression {
  void interpret(Context ctx);
}
