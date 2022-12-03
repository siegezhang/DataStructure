package jdk17;

import java.util.Collection;

public class SwitchTest {
    void printObject(Object obj) {
        final var representation = switch (obj) {
            case String s -> "String: \"" + s + "\"";
            case Collection<?> c -> "Collection (size = " + c.size() + ")";
            case null, default -> "Other object: " + obj;
        };
        System.out.println(representation);
    }
}
