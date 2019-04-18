package designpattern.abstractfactory;

public class ConcreteFactoryA implements AbstractFactory {
  @Override
  public AbstractProduct createProduct() {
    return new ProductA();
  }
}
