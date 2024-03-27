package validator;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class User {

  @NotEmpty(message = "firstname may be empty")
  private String firstname;

  @NotEmpty(message = "middlename may be empty", groups = Default.class)
  private String middlename;

  @NotEmpty(message = "lastname may be empty", groups = GroupA.class)
  private String lastname;

  @NotEmpty(message = "country may be empty", groups = GroupB.class)
  private String country;

  public interface GroupA {}

  public interface GroupB {}

  // 组序列
  @GroupSequence({Default.class, GroupA.class, GroupB.class})
  public interface Group {}
}
