package either;

import java.util.function.Function;

public class Either<L, R> {
  private final L left;
  private final R right;

  private Either(L l, R r) {
    left = l;
    right = r;
  }

  public static <L, R> Either<L, R> left(L l) {
    return new Either<>(l, null);
  }

  public static <L, R> Either<L, R> right(R r) {
    return new Either<>(null, r);
  }

  public <T> Either<L, T> map(Function<R, T> function) {
    if (isLeft()) {
      return left(left);
    }
    return right(function.apply(this.right()));
  }

  public <T> T mapLeft(Function<L, T> function) {
    if (isLeft()) return function.apply(this.left());
    return null;
  }

  public L left() {
    return left;
  }

  public R right() {
    return right;
  }

  public boolean isLeft() {
    return left != null;
  }
}
