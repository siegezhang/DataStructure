package designpattern.interpretor;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class ApiTest {

  @Test
  public void test() {
    // String rule = "api_error_per_minute > 100 || api_count_per_minute > 10000";
    // AlertRuleInterpreter alertRuleInterpreter = new AlertRuleInterpreter(rule);
    String rule = "api_error_per_minute > 100 && api_count_per_minute > 10000";
    AlertRuleInterpreter alertRuleInterpreter = new AlertRuleInterpreter(rule);
    HashMap<String, Long> statsMap =
        new HashMap<>() {
          {
            //  put("api_error_per_minute", 99L);
            // put("api_count_per_minute", 121L);

            put("api_error_per_minute", 101L);
            put("api_count_per_minute", 10000L);
          }
        };
    boolean alertInterpret = alertRuleInterpreter.interpret(statsMap);
    String alert = alertInterpret ? "超过阈值，危险！！" : "目前运行良好";
    System.out.println("预警结果为：alert:" + alert);
  }
}
