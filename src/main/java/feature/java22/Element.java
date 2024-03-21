package feature.java22;

import java.awt.*;

/** validating values passed to super() in a derived class constructor */
public class Element {
  int atomicNumber;
  Color color;

  public Element(int atomicNumber, Color color) {
    if (color == null) throw new IllegalArgumentException("color is null");
    this.atomicNumber = atomicNumber;
    this.color = color;
  }
  // rest of the code
}
