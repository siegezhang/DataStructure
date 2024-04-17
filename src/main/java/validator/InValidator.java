package validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InValidator implements ConstraintValidator<In, Integer> {
    private final Set<Integer> values = new HashSet<>();
    private String msg = null;

    @Override
    public void initialize(In constraintAnnotation) {
        for (int value : constraintAnnotation.values()) {
            this.values.add(value);
        }
        String msg = values.stream().map(Object::toString).collect(Collectors.joining(",", "[", "]"));
        this.msg = String.format("只能取值%s", msg);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean contains = values.contains(value);
        if (contains) {
            return true;
        }

        if (context.getDefaultConstraintMessageTemplate().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(this.msg).addConstraintViolation();
        }
        return false;
    }
}
