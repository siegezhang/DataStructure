package designpattern.abstractfactory;

public class FactoryProducer {
  public static AbstractFactory getAbstractFactory(String type) {
    if ("productA".equals(type)) return new ConcreteFactoryA();
    else return new ConcreteFactoryB();
  }
}
