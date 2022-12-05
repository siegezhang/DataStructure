package designpattern.visitor;

import org.junit.jupiter.api.Test;

public class VisitorTest {
  @Test
  public void test() {
    Visitor visitor = new Visitor();
    BeVisitor beVisitor = new BeVisitor();
    beVisitor.accept(visitor);
  }
}
