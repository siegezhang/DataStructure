package designpattern.abstractfactory;

import org.junit.jupiter.api.Test;

public class AbstractFactoryTest {
  @Test
  public void test() {
    AbstractFactory abstractFactory = FactoryProducer.getAbstractFactory("productA");
    abstractFactory.createProduct();
  }
}
