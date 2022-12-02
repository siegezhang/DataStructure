package jdk17;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class RecordTest {
    @NotNull
    private static Object getPassenger() {
        return new Passenger(new Name("siege", "zhang"),
                new PhoneNumber("086", "18516100450"),
                new Country("086", "China"),
                new Country("001", "USA"));
    }

@Test
public void testRecord(){
    if (getPassenger() instanceof Passenger(Name (var fName, var lName),
            PhoneNumber phoneNumber,
            Country from,
            Country (String countryCode, String countryName) )) {
        if (fName != null && countryCode != null) {
            assert fName.startsWith("siege") && countryCode.equals("001");
        }
    }
}

    @Test
    public void testRecord1(){
        Gift<WristWatch> obj=getWatch();
        unwrap(obj);
    }

    private Gift<WristWatch> getWatch() {
        return new Gift<>(new WristWatch());
    }

    void unwrap(Gift<WristWatch> obj) {
        if (obj instanceof Gift<WristWatch> (var watch)) {
            System.out.println(watch);
        }
    }
}



record Name       (String fName, String lName) { }
record PhoneNumber(String areaCode, String number) { }
record Country    (String countryCode, String countryName) { }
record Passenger  (Name name,
                   PhoneNumber phoneNumber,
                   Country from,
                   Country destination) { }

class WristWatch {}
record Gift<T>(T t) {}
