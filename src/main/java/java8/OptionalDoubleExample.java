package java8;

import java.util.OptionalDouble;

/**
 * Created by siege on 2017/8/30.
 */
public class OptionalDoubleExample {
    public static void main(String[] args) {
        OptionalDouble optionalDouble=OptionalDouble.of(5.0);
        System.out.println("optionalDouble.isPresent() ="+optionalDouble.isPresent());
        System.out.println("optionalDouble.orElse ="+optionalDouble.orElse(6.0));
        System.out.print("\n1 - optionalDouble.ifPresent  ");
        optionalDouble.ifPresent(System.out::print);
        System.out.print("\n2 - optionalDouble.ifPresent  ");
        optionalDouble.ifPresent(d->System.out.println("value="+d));
    }
}
