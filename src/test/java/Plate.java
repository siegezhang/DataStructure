import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

public class Plate<T> {
    private T   item;
    public Plate(T t){
        item=t;
    }
    public  void set(T t){

        item=t;
    }

    public T get(){
        return item;
    }

    public static void main(String[] args) {
        //DoubleSupplier
        //LongSupplier

    }
}
