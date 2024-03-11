package validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotBlankFieldsValidator.class})
public @interface NotBlankFields {

  String message() default "所有字段均不能为空";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
