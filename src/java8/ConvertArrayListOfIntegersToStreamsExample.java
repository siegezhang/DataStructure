package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by siege on 2017/8/30.
 */
public class ConvertArrayListOfIntegersToStreamsExample {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(11);
        listOfIntegers.add(12);

        System.out.println("2. Convert list (ArrayList) of Integer to stream");

        Stream<Integer> integerStream=listOfIntegers.stream();
        integerStream.forEach(System.out::println);
    }
}
