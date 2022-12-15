package either;

import java.util.function.Function;

public class Either<L, R> {
  private L left = null;
  private R right = null;

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

  public <T> T map(Function<R, T> function) {
    if (isLeft()) return null;
    return function.apply(this.right());
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
