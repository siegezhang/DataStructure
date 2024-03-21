package feature.java22;

import java.awt.*;

/**
 * Until Java 21, no statements were allowed to execute before super(). Here’s one of the ways we
 * developers found a workaround by defining and calling static methods (static methods belong to a
 * class and not to instances and can be executed before any instance of a class exists):
 */
public class IndustryElement extends Element {
  private static final int MIN_ATOMIC_NUMBER = 1;
  private static final int MAX_ATOMIC_NUMBER = 118;

  public IndustryElement(int atomicNumber, Color color) {
    super(checkRange(atomicNumber, MIN_ATOMIC_NUMBER, MAX_ATOMIC_NUMBER), color);
  }

  private static int checkRange(int value, int lowerBound, int upperBound) {
    if (value < lowerBound || value > upperBound)
      throw new IllegalArgumentException("Atomic number out of range");
    return value;
  }
}
