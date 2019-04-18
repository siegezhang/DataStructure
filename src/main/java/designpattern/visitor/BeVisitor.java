package designpattern.visitor;
/**
 * *
 *
 * <p>观察者模式，这里BeVisitor应该是接口，这里简化了
 */
public class BeVisitor {
  private String property;

  /***
   *这里可以控制访问者的权限
   */
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void beVisitMethod() {
    System.out.println("beVisitMethod");
  }

  public String getProperty() {
    return property;
  }
}
