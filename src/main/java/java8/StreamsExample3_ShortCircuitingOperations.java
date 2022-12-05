package java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/** Created by siege on 2017/8/30. */
public class StreamsExample3_ShortCircuitingOperations {
  @Test
  public void test() {
    List<String> stringList = new ArrayList<>();
    stringList.add("a");
    stringList.add("b");
    stringList.add("a");
    System.out.println("\nUse stream for finding distinct elements and and display");

    Stream<String> stream = stringList.stream();
    stream.distinct().forEach(System.out::println);

    System.out.println("\n1.Short-circuiting stateful intermediate operations");
    stream = stringList.stream();
    System.out.println("findFirst() method >");
    Optional<String> optional1 = stream.findFirst();
    System.out.println(optional1.get());

    stream = stringList.stream();
    System.out.println("stream.findAny() >");
    Optional<String> optional2 = stream.findAny();
    System.out.println(optional2.get());

    stream = stringList.stream();
    boolean anyRecordStartsWithA = stream.anyMatch(s -> s.startsWith("a"));
    System.out.println("\n2.any Record Starts With 'a'  = " + anyRecordStartsWithA);

    stream = stringList.stream();
    boolean allRecordStartsWithA = stream.allMatch(s -> s.startsWith("a"));
    System.out.println("\n3.all Record Starts With 'a'  = " + allRecordStartsWithA);

    stream = stringList.stream();
    boolean noneRecordStartsWithB = stream.noneMatch(s -> s.matches("b"));
    System.out.println("\n4.none Record Starts With 'b' = " + noneRecordStartsWithB);

    stream = stringList.stream();
    long countOfRecordsStartsWithA = stream.filter(s -> s.startsWith("a")).count();
    System.out.println("\n5.count Records Starts With 'a' = " + countOfRecordsStartsWithA);
  }
}
