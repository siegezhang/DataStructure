package validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
  @Test
  public void test() {
    Person person = new Person();
    person.setName("fsx");
    person.setAge(5);
    person.setHobbies(Arrays.asList("足球", "篮球"));
    person.setChild(new Person());

    Set<ConstraintViolation<Person>> result =
        Validation.buildDefaultValidatorFactory().getValidator().validate(person);

    // 对结果进行遍历输出
    result.stream()
        .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
        .forEach(System.out::println);
  }
}
