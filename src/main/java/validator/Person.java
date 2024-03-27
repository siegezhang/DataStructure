package validator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;

@GroupSequenceProvider(PersonGroupSequenceProvider.class)
@Getter
@Setter
@ToString
public class Person {

  @NotNull private String name;

  @NotNull
  @Range(min = 10, max = 40)
  private Integer age;

  @NotNull
  @Size(min = 3, max = 5)
  @NotNull(groups = {WhenAge20And30Group.class, WhenAge30And40Group.class})
  private List<String> hobbies;

  /** 定义专属的业务逻辑分组 */
  public interface WhenAge20And30Group {}

  public interface WhenAge30And40Group {}

  // 级联校验
  @Valid @NotNull private Person child;
}
