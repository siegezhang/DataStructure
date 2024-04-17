package validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, YesNoEnum> {

    @Override
    public YesNoEnum convert(String source) {
        for (YesNoEnum enum1 : YesNoEnum.values()) {
            if (StringUtils.equals(String.valueOf(enum1.status), source)) {
                return enum1;
            }
        }
        return null;
    }
}
