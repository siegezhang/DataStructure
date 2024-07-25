package annotation.desensitized;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Desensitized {
    //替换成自己定义的枚举
    DesensitizedType type() default DesensitizedType.CUSTOM;

    /**
     * 当type不指定时,可自定义脱敏起始位置(包含)
     */
    int startInclude() default 0;

    /**
     * 当type不指定时,可自定义脱敏结束位置(不包含) ,-1代表字符串长度
     */
    int endExclude() default -1;
}
