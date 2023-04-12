package generic;

public abstract class BaseGenericAnimal<T extends BaseGenericAnimal> {
  String name;

  protected BaseGenericAnimal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract T reproduce();

  public abstract T mate(T oher);
}

class Cat extends BaseGenericAnimal<Cat> {
  public Cat(String name) {
    super(name);
  }

  @Override
  public Cat reproduce() {
    return new Cat(this.name + "junior");
  }

  @Override
  public Cat mate(Cat oher) {
    return new Cat(this.name + " " + oher.name);
  }
}
