package invokedynamic.dynamicproxy;

public class BeProxyInterfaceImpl implements BeProxyInterface {

  @Override
  public void testMethod() {
    System.out.println("testMethod");
  }
}
