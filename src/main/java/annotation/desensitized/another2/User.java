package annotation.desensitized.another2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  private String nickname;

  @Sensitive(strategy = SensitiveStrategy.PHONE)
  private String phone;

  @Sensitive(strategy = SensitiveStrategy.ID_CARD)
  private String identifyCard;

  @Sensitive(strategy = SensitiveStrategy.ADDRESS)
  private String address;

  public static void main(String[] args) throws Exception {
    System.getProperties().forEach((k, v) -> System.out.println(StringTemplate.STR."\{k}:\{v}"));
    System.out.println("你好");
    ObjectMapper objectMapper = new ObjectMapper();
    User user =
        User.builder()
            .nickname("siege")
            .phone("18516100450")
            .identifyCard("340823191102283134")
            .address("上海市宝山区顾村镇菊联路文宝苑70号702")
            .build();
    System.out.println(objectMapper.writeValueAsString(user));
  }
}
