package designpattern.visitor;
/**
 * *
 *
 * <p>观察者模式，这里Visitor应该是接口，这里简化了
 */
public class Visitor {
  public void visit(BeVisitor beVisitor) {
    beVisitor.beVisitMethod();
    System.out.println(beVisitor.getProperty());
  }
}
