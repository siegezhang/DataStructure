import org.junit.Test;

public class TestGeneric1<AnyType extends Comparable<? super AnyType>> {

    @Test
    public void test() {
        TestGeneric1<Square> p=null;
    }
}
