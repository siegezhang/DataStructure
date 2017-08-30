package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by siege on 2017/8/30.
 */
public class StreamsExample2_infiniteSequentialStream {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("a");
        System.out.println("Use stream for finding distinct elements and and display");

        Stream<String> stream=stringList.stream();
        stream.distinct().forEach(System.out::println);

        System.out.println("\n1. infinite sequential unordered stream");
        System.out.println("generate() method returns an infinite sequential unordered stream");
        Stream<String> stream1=Stream.generate(()->{return "infiniteUnorderedStream";});
        stream1.forEach(System.out::println);

        System.out.println("\n2. infinite sequential ordered stream");
        System.out.println("iterate() method returns an infinite sequential ordered stream");
        Stream<String> stream2=Stream.iterate("xyz",n->n.toUpperCase());
        stream2.forEach(System.out::println);

    }
}
