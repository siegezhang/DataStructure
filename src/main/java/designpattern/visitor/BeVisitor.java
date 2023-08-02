package designpattern.visitor;

public class BeVisitor implements IBeVisitor {
  private String property;

  /***
   *这里可以控制访问者的权限
   */
  @Override
  public void accept(IVisitor visitor) {
    visitor.visit(this);
  }

  public void beVisitMethod() {
    System.out.println("beVisitMethod");
  }

  public String getProperty() {
    return property;
  }
}
