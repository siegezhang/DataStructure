package annotation.desensitized.another3;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Student {

  @MobileNoDesensitize private String mobile;

  public static void main(String[] args) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Student student = new Student();
    student.setMobile("13156789981");
    // {"mobile":"131****9981"}
    System.out.println(objectMapper.writeValueAsString(student));
  }
}
