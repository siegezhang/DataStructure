package json;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class JsonTest {

  @Test
  public void test1() {
    JSONObject jsonObject = new JSONObject();
    JSONObject newJsonObject = new JSONObject();
    jsonObject.put("page", 1);
    jsonObject.put("pageSize", 10);
    jsonObject.forEach(
        (key, value) -> {
          key = StringUtils.capitalize(key);
          newJsonObject.put(key, value);
        });
      jsonObject.put("pageSize", 10);
    System.out.println(jsonObject);
  }
}
