public class TestGeneric1<AnyType extends Comparable<? super AnyType>> {

    public static void main(String[] args) {
        TestGeneric1<Square> p=null;
    }
}
