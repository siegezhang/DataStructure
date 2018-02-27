package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by siege on 2017/8/30.
 */
public class StreamsExceptionExample {
    public static void main(String[] args) {
        List<String> stringList=new ArrayList<>();
        stringList.add("ank");
        Stream<String> stream=stringList.stream();
        stream.forEach(System.out::print);

        /**
         * UNCOMMENT this line to avoid - java.lang.IllegalStateException: stream has already been operated upon or closed
         */
        //stream = StringList.stream(); // Convert list (ArrayList) to stream

        //Below will throw - Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        //Exception could be avoided if above line is uncommented
        stream.forEach(System.out::println);

        /**
         * Why it throws - Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed?
         * Because streams can't be used again
         *
         * Message itself is self-explanatory - stream has already been operated upon or closed -
         * in our case - stream has already been operated upon
         *
         * Solution -
         * Create new stream every time for some new operation.
         */
    }
}
