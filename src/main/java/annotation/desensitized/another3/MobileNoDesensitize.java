package annotation.desensitized.another3;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = MobileNoDesensitization.class)
@Documented
public @interface MobileNoDesensitize {}
