public class TestGenetic {
    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr){
        int maxIndex=0;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i].compareTo(arr[maxIndex+1])>0)
                maxIndex=i;
        }
        return arr[maxIndex];
    }
    public static void main(String[] args) {
        Shape[] shapes={new Shape(),new Shape()};
        Square[] squares={new Square(),new Square()};
        Shape shape=findMax(shapes);
        Shape shape1=findMax(squares);
        Square square=findMax(squares);
    }
}
