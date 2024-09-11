import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MyTest {

  public static void main(String[] args) throws IOException {
    //        for (int i = 0; i < 50; i++) {
    //
    //            System.out.println(UUID.randomUUID().toString());
    //        }
    String user = "{\"姓名\":\"张三\",\"城市\":\"北京\",\"电话\":\"1212323\"}";
    //        UserBo userBo1 = JSONObject.parseObject(user, UserBo.class);
    ObjectMapper objectMapper = new ObjectMapper();
    // UserBo userBo1 = objectMapper.readValue(user, UserBo.class);
    UserBo userBo2 =
        objectMapper.readValue(
            new ByteArrayInputStream(user.getBytes(StandardCharsets.UTF_8)), UserBo.class);
    System.out.println(userBo2);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserBo implements Serializable {

    @JsonProperty("姓名")
    private String name;

    @JsonProperty("电话")
    private String phone;

    @JsonProperty("城市")
    private String city;
  }
}
