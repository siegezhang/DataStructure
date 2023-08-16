package aviator;

import com.googlecode.aviator.AviatorEvaluator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class AviatorTest {
  @Test
  public void test() {
    Map<String, Object> env = new HashMap<>();
    env.put("email", "killme2008@gmail.com");
    String username =
        (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1:'unknow'", env);
    System.out.println(username);

    String expression = "2 + 3 * 4";
    Long value = (Long) AviatorEvaluator.execute(expression);
    System.out.println(value);


  }
}
