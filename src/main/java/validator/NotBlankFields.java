package validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NotBlankFields.NotBlankFieldsValidator.class})
public @interface NotBlankFields {

    String message() default "所有字段均不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotBlankFieldsValidator implements ConstraintValidator<NotBlankFields, List<Object>> {

        @Override
        public boolean isValid(List<Object> value, ConstraintValidatorContext context) {
            if (value == null || value.isEmpty()) {
                return false;
            }
            for (Object o : value) {
                if (o == null) {
                    return false;
                }
                if (o instanceof String s) {
                    return !s.isBlank();
                }
            }
            return true;
        }
    }
}
