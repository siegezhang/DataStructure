package validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValueValidator.Validator.class)
public @interface EnumValueValidator {

    Logger log = LoggerFactory.getLogger(EnumValueValidator.class);

    String message() default "参数有误";

    Class<? extends Enum<?>> enumClass();

    String enumMethod();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<EnumValueValidator, Object> {
        private Class<? extends Enum<?>> enumClass;
        private String enumMethod;

        @Override
        public void initialize(EnumValueValidator constraintAnnotation) {
            enumMethod = constraintAnnotation.enumMethod();
            enumClass = constraintAnnotation.enumClass();
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            // 值没传的情况，直接返回true
            if (Objects.isNull(o) || StringUtils.isEmpty(o.toString())) return Boolean.TRUE;
            if (enumClass == null || StringUtils.isEmpty(enumMethod)) return Boolean.TRUE;
            Class<?> vclass = o.getClass();
            try {
                Method method = enumClass.getMethod(enumMethod, vclass);
                if (!Boolean.TYPE.equals(method.getReturnType()) &&
                        !Boolean.class.equals(method.getReturnType())) {
                    throw new RuntimeException("校验方法不是布尔类型!");
                }
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException("校验方法不是静态方法!");
                }
                method.setAccessible(true);
                Boolean res = (Boolean) method.invoke(null, o);
                return res != null ? res : false;
            } catch (NoSuchMethodException e) {
                log.error("NoSuchMethodException:{}", e);
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                log.error("IllegalAccessException:{}", e);
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                log.error("InvocationTargetException:{}", e);
                throw new RuntimeException(e);
            }
        }
    }

}
