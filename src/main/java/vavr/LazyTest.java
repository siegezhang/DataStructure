package vavr;

import io.vavr.Lazy;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;

/** */
public class LazyTest {
  @Test
  public void test() {
    Lazy<Double> lazy = Lazy.of(Math::random);
    lazy.isEvaluated(); // = false
    lazy.get(); // = 0.123 (random generated)
    lazy.isEvaluated(); // = true
    lazy.get(); // = 0.123 (memoized)
  }

  @Test
  public void testAPI() {
    // 构造 Option
    var some = Some(1);
    var none = None();

    // 构造 Future
    var future = Future(() -> "ok");

    // 构造 Try
    var tryInit = Try(() -> "ok");
  }

  public String bmiFormat(double height, double weight) {
    double bmi = weight / (height * height);
    return Match(bmi)
        .of(
            // else if (bmi < 18.5)
            Case($(v -> v < 18.5), () -> "有些许晃荡！"),
            // else if (bmi < 25)
            Case($(v -> v < 25), () -> "继续加油哦！"),
            // else if (bmi < 30)
            Case($(v -> v < 30), () -> "你是真的稳！"),
            // else
            Case($(), () -> "难受！"));
  }
}
