package validator;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Getter
@Setter
@ToString
public class Person {

    @NotNull
    private String name;
    @NotNull
    @Range(min = 10, max = 40)
    private Integer age;

	@NotNull
    @Size(min = 3, max = 5)
    private List<String> hobbies;

    // 级联校验
    @Valid
    @NotNull
    private Person child;
}
