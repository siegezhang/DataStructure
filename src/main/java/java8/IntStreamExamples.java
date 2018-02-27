package java8;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by siege on 2017/8/31.
 */
public class IntStreamExamples {
    public static void main(String[] args) {
        //Before Java 8 - Find all numbers between 1 to 5
        for (int i = 0; i <= 5; i++) {
            if (i%2==0)
                System.out.println(i);
        }

        IntStream intStream=IntStream.of(1,2,3,4,5);
        IntStream intStream2=IntStream.of(new int[]{1,2,3,4,5});
        IntStream intStream3=IntStream.range(1,6);

        intStream.forEach(System.out::println);
        System.out.println("--");
        IntStream.range(1,6).filter(n->n%2==0).forEach(System.out::println);

        System.out.println("--g1");
        IntStream.of(1,2,3,4,5).filter(n->n>2).forEach(System.out::println);

        System.out.println("--g2");
        IntStream.of(1,2,3,4,5).filter(n->n>2).filter(n->n%2==0).forEach(System.out::println);

        System.out.println("--");
        IntStream.of(1,2,3,4,5).filter(n->n%2==1).forEach(System.out::println);

        System.out.println("--x1");
        int sum=IntStream.of(1,2,3,4,5).sum();
        System.out.println(sum);

        System.out.println("--x13-");
        int sum1=IntStream.of(1,2,3,4,5).filter(n->n%2==0).sum();
        System.out.println(sum1);

        System.out.println("--x13-");
        OptionalInt max=IntStream.of(9,5,3,12,4).max();
        System.out.println("max="+max.getAsInt());

        OptionalInt min=IntStream.of(9, 5, 3, 12, 4).min();
        System.out.println("min="+min.getAsInt());

        long count=IntStream.of(9, 5, 3, 12, 4).count();
        System.out.println("count="+count);

        OptionalDouble average=IntStream.of(1,2,3).average();
        System.out.println("average="+average.getAsDouble());

        System.out.println("--x1");
        Stream<Integer> i1=Stream.of(1,2,3,4);

        OptionalInt optionalInt=IntStream.of(1,2,3,4,5).findFirst();
        System.out.println("optionalInt.getAsInt() ="+optionalInt.getAsInt());

        OptionalInt optionalInt1=IntStream.of(1,2,3,4,5).findAny();
        System.out.println("optionalInt1.getAsInt() ="+optionalInt1.getAsInt());

        boolean anyRecordGreaterThan=IntStream.of(1,2,3,4,5).anyMatch(n->n>2);
        System.out.println("Any Record greater than 2  ="+anyRecordGreaterThan);

        boolean allRecordGreaterThan=IntStream.of(1,2,3,4,5).allMatch(n->n>2);
        System.out.println("All Record greater than 2  ="+allRecordGreaterThan);

        boolean noneRecordGreaterThan=IntStream.of(1,2,3,4,5).noneMatch(n->n>6);
        System.out.println("None Record greater than 6  ="+noneRecordGreaterThan);

        long countRecordsGreaterThan=IntStream.of(1,2,3,4,5).filter(n->n>2).count();
        System.out.println("Count number of records greater than 2 ="+countRecordsGreaterThan);

        System.out.println("--3");

        OptionalInt reduced11=IntStream.of(1,2,3,4,5).reduce((x,y)->x+y);
        System.out.println(reduced11.getAsInt());

        System.out.println("--2");
        int reduced2=IntStream.of(1,2,3,4,5).reduce(10,(x,y)->x+y);
        System.out.println(reduced2);




    }
}
