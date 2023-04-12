package generic;

import generic.ConvriantTest.Cat;
import java.util.Arrays;
import java.util.List;

public class ContravarianceTest {
  public static void main(String[] args) {
    List<Cat> catList = Arrays.asList(new Cat("Mittens"), new Cat("Petunia"));
    List<ConvriantTest.Animal> animalList = Arrays.asList(new Cat("Spike"), new Cat("Mittens"));
    List<? super Cat> baseList;

    // GenericType<? super Cat> is assigned a reference to GenericType<Animal>
    baseList = animalList;
    baseList.add(new Cat("GenericMitens2"));

    // GenericType<? super Cat> is assigned a reference to GenericType<Cat>
    baseList = catList;
    baseList.add(new Cat("Mittens2"));

    // No way for us to know what's in the first position of the list.
    // It may be a cat, it may be an animal, it may be an object.
    Object firstItem = baseList.get(0);

    // Error, not compilable:
    // baseList.add(new Animal("petunia"));
  }
}
