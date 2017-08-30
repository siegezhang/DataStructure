package java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by siege on 2017/8/30.
 */
public class StreamsExample_createStream_filter_display_ {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ank");
        stringList.add("sam");
        stringList.add("az");
        stringList.add("neh");
        stringList.add("ad");

        Stream<String> stream=stringList.stream();
        System.out.println("1. Use stream for filtering and display");
        stream.filter(s->s.startsWith("a")).forEach(System.out::println);

        stream=stringList.stream();
        System.out.println("\n2.Use stream for filtering and display");
        stream.filter(s->s.startsWith("a")).forEach(s->System.out.println("value = "+s));

        stream = stringList.stream();
        System.out.println("\n3.Use stream for filtering and display");
        stream.forEach(s->{
            if(s.startsWith("a"))
                System.out.println(s);
        });

        stream = stringList.stream();
        System.out.println("\n4.Use stream for filtering, sorting and display (in sorted manner - ASCENDING order )");
        stream.filter(s->s.startsWith("a")).sorted().forEach(System.out::println);

        stream = stringList.stream();
        System.out.println("\n5.Use stream for filtering, sorting and display(in sorted manner - DESCENDING order)");
        stream.filter(s->s.startsWith(s)).sorted((a,b)->b.compareTo(a)).forEach(System.out::println);

        stream = stringList.stream();
        System.out.println("\n6.Use stream for filtering, sorting and display(in sorted manner - DESCENDING order)");
        stream.filter(s->s.startsWith("a")).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        stream = stringList.stream();
        System.out.println("\n7.Use stream for filtering, UPPERCASE conevrsion, sorting and display");
        stream.filter(s->s.startsWith("a")).map(s->s.toUpperCase()).sorted().forEach(System.out::println);

        stream = stringList.stream();
        System.out.println("\n8.Use stream for filtering, UPPERCASE conversion, sorting and display");
        stream.filter(s->s.startsWith("a")).map(String::toUpperCase).sorted().forEach(System.out::println);




    }
}
