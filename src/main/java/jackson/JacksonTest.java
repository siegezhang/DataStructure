package jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JacksonTest {

  /**
   * 测试Jackson的简单对象的序列化和反序列化
   *
   * @throws JsonProcessingException
   */
  @Test
  public void test() throws JsonProcessingException {
    Student s = Student.builder().name("小明").build();
    System.out.println(new ObjectMapper().writeValueAsString(s));
    Student student =
        new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(s), Student.class);
    System.out.println(student.getName());
  }

  /**
   * 测试Jackson的List对象的序列化和反序列化
   *
   * @throws JsonProcessingException
   */
  @Test
  public void test1() throws JsonProcessingException {
    Student s = Student.builder().name("小明").build();
    Student s1 = Student.builder().name("小红").build();
    System.out.println(new ObjectMapper().writeValueAsString(Arrays.asList(s, s1)));
    List<Student> students =
        new ObjectMapper()
            .readValue(
                new ObjectMapper().writeValueAsString(Arrays.asList(s, s1)),
                new TypeReference<>() {});
    System.out.println(students);
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Student {
    @JsonProperty("name_stu")
    public String name;
  }
}
