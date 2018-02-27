package java8;

import java.util.Optional;

/**
 * Created by siege on 2017/8/30.
 */
public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional=Optional.of("hello");
        System.out.println("optional.isPresent() ="+optional.isPresent());
        System.out.println("optional.get() ="+optional.get());
        System.out.println("optional.orElse="+optional.orElse("alternateValue"));
        System.out.print("\n1 - optional.ifPresent  ");
        optional.ifPresent(System.out::println);
        System.out.print("\n2 - optional.ifPresent  ");
        optional.ifPresent(msg->System.out.println(msg.concat(" Ankit")));

        new Thread(()->{
            System.out.println("hello11111");
        }).start();

    }
}
