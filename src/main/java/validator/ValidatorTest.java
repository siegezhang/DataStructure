package validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

public class ValidatorTest {
    @Test
    public void test() {
        Person person = new Person();
        person.setName("fsx");
        person.setAge(25);
        person.setHobbies(Arrays.asList("足球", "篮球"));
        Person child = new Person();
        child.setName("child");
        child.setAge(10);
        person.setChild(child);

        Set<ConstraintViolation<Person>> result =
                Validation.buildDefaultValidatorFactory().getValidator().validate(person);

        // 对结果进行遍历输出
        result.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .forEach(System.out::println);
    }

    @Test
    public void test1() {
        User user = new User();
        // 此处指定了校验组是：User.Group.class
        Set<ConstraintViolation<User>> result =
                Validation.buildDefaultValidatorFactory().getValidator().validate(user, User.Group.class);

        // 对结果进行遍历输出
        result.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .forEach(System.out::println);
    }

    /**
     * @GroupSequence提供的组序列顺序执行以及短路能力，在很多场景下是非常非常好用的。
     */
    @Test
    public void test2() {
        User user = new User();
        user.setFirstname("f");
        user.setMiddlename("s");
        // 此处指定了校验组是：User.Group.class
        Set<ConstraintViolation<User>> result =
                Validation.buildDefaultValidatorFactory().getValidator().validate(user, User.Group.class);

        // 对结果进行遍历输出
        result.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        User1 user = new User1();
        user.setName("siege");
        user.setStatus(2);
        Set<ConstraintViolation<User1>> result =
                Validation.buildDefaultValidatorFactory().getValidator().validate(user);

        result.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .forEach(System.out::println);
    }
}
