package feature.java22;

import java.awt.*;

/**
 * Compare a base class with a seed and the derived class with a plant that is formed using it. A
 * seed needs to be fully formed, before any part of the plan or its processes can be accessed.
 * Similarly, the constructor of a base class must execute completely, before any member variables
 * or methods of a derived class can be called. So with this constraint, the Java platform was
 * trying to protect the state of your instances. Also, if you do not explicitly call the
 * constructor of a base class in a derived class constructor, the compiler inserts a call to the
 * implicit, no-argument, base class constructor. In short, at least one base class constructor
 * always gets executed for a derived class, whether you do that explicitly or not. This was the
 * case since Java 1.0.
 *
 * <p>https://blog.jetbrains.com/idea/2024/02/constructor-makeover-in-java-22/</p>
 */
public class IndustryElementAfterJDK22 extends Element {
    private static final int MIN_ATOMIC_NUMBER = 1;
    private static final int MAX_ATOMIC_NUMBER = 118;

    public IndustryElementAfterJDK22(int atomicNumber, Color color) {
        super(atomicNumber, color);
        if (atomicNumber < MIN_ATOMIC_NUMBER || atomicNumber > MAX_ATOMIC_NUMBER)
            throw new IllegalArgumentException("Atomic number out of range");
        // super(atomicNumber, color);
    }
}
