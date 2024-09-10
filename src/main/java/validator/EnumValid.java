package validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {EnumValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValid {

    /**
     * 不合法时 抛出异常信息
     */
    String message() default "值不合法";

    /**
     * 校验的枚举类
     *
     * @return
     */
    Class<?> enumClass();

    /**
     * 对应枚举类中需要比对的字段
     *
     * @return
     */
    String field() default "code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}