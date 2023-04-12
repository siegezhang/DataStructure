package generic;

/**
 * This differs from the return type of ‘reproduce’ as defined in the base-class.Animal . However,
 * despite this difference in function definition, ‘reproduce’ in Cat is an override of ‘reproduce’
 * in Animal. There is no way to call ‘reproduce’ on a Cat and get an instance of the
 * base-class.Animal. You will always get cats. <strong>java has automatic covariance on function
 * return parameters and Java is covariant on return types only.</strong>
 *
 * <p>The function Cat mate(Animal) in Cat.class indeed overrides the Animal mate(Animal) in
 * Animal.class. Overriding happens when the difference is only in the output parameter of the
 * function . When there is a difference in both a return parameter and input parameter in the
 * derived function, it (the function in the derived class) is treated as a completely new function,
 * independent of anything in the base class. Since this completely different function happens to
 * share the same name (mate), we are dealing with an overload
 *
 * <p>The solution for this conundrum comes in the form of generics. If we want cats to mate
 * exclusively with other cats and no other animals, we need to use generics. BaseGenericAnimal
 * example illustrates how generic type parameters will ensure animals remain segregated by type
 * when mating/reproducing:
 */
public class ConvriantTest {
  static class Animal {
    String name;

    public Animal(String name) {}

    public String getName() {
      return name;
    }

    public Animal mate(Animal oher) {
      System.out.println("in base animal");
      return new Animal(this.name + " " + oher.getName());
    }

    public Animal reproduce() {
      System.out.println("Base animal reproduces");
      return new Animal(name);
    }
  }

  static class Cat extends Animal {
    public Cat(String name) {
      super(name);
    }

    @Override
    public Cat reproduce() {
      System.out.println("Cat reproduces");
      return new Cat(this.name + "Junior");
    }

    @Override
    public Cat mate(Animal a) {
      System.out.println("in cat");
      return new Cat(this.name + " " + a.getName());
    }
  }
}
