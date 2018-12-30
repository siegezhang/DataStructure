package either;

public class Either<A, B> {
  private A left = null;
  private B right = null;

  private Either(A a, B b) {
    left = a;
    right = b;
  }

  public static <A, B> Either<A, B> left(A a) {
    return new Either<A, B>(a, null);
  }

  public static <A, B> Either<A, B> right(B b) {
    return new Either<A, B>(null, b);
  }

  public A left() {
    return left;
  }

  public B right() {
    return right;
  }

  public boolean isLeft() {
    return left != null;
  }


}
