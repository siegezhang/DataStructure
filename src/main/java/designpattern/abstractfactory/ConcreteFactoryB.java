package designpattern.abstractfactory;

public class ConcreteFactoryB implements AbstractFactory {
  @Override
  public AbstractProduct createProduct() {
    return new ProductB();
  }
}
