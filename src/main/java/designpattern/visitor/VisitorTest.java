package designpattern.visitor;

import org.junit.jupiter.api.Test;

public class VisitorTest {
  @Test
  public void test() {
    IVisitor visitor = new Visitor();
    IBeVisitor beVisitor = new BeVisitor();
    beVisitor.accept(visitor);
  }
}
