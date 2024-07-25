package annotation.desensitized;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Aspect
@Component
@Slf4j
public class DataDesensitizedAspect {
    @AfterReturning(pointcut = "@annotation(dd)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, DataDesensitized dd, Object result) {
        //TODO 这里可以根据组织架构角色控制是否脱敏
        boolean need = true;
        if (!need) {
            return;
        }
        if (result instanceof List) {
            List list = (List) result;
            for (Object obj : list) {
                objReplace(obj);
            }
        } else {
            objReplace(result);
        }
    }

    public static <T> void objReplace(T t) {
        try {
            Field[] declaredFields = ReflectUtil.getFields(t.getClass());
            for (Field field : declaredFields) {
                Desensitized des = field.getAnnotation(Desensitized.class);
                //被脱敏注解修饰且string类型
                if (des != null &&
                        "class java.lang.String".equals(field.getGenericType().toString())) {
                    Object fieldValue = ReflectUtil.getFieldValue(t, field);
                    if (fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) {
                        continue;
                    }
                    String value = fieldValue.toString();
                    String hide = "";
                    if (des.type() == DesensitizedType.CUSTOM) {
                        int startInclude = des.startInclude();
                        int endExclude = des.endExclude();
                        if (endExclude == -1) {
                            endExclude = value.length();
                        }
                        hide = StrUtil.hide(value, startInclude, endExclude);
                    } else {
                        DesensitizedUtil.DesensitizedType type =
                                DesensitizedUtil.DesensitizedType.valueOf(des.type().toString());
                        hide = DesensitizedUtil.desensitized(value, type);
                    }
                    ReflectUtil.setFieldValue(t, field, hide);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
