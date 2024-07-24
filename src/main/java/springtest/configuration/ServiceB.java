package springtest.configuration;

public class ServiceB {
  private ServiceA serviceA;

  public ServiceB(ServiceA serviceA) {
    this.serviceA = serviceA;
  }

  @Override
  public String toString() {
    //return STR."ServiceB{serviceA=\{serviceA},serviceB=\{this.hashCode()}}";
    return null;
  }
}
