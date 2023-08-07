package designpattern.interpretor;

import designpattern.interpretor.expression.AbstractExpression;

public class NonterminalExpression implements AbstractExpression {

  private AbstractExpression left;

  private AbstractExpression right;

  public NonterminalExpression(AbstractExpression left, AbstractExpression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public void interpret(Context context) {
    System.out.println("非终结符表达式进行处理中~");
    // 递归调用每一个组成部分的 interpret()方法
  }
}
