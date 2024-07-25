package annotation.desensitized;

import lombok.Data;

@Data
public class OrderDetailsVo {
    private String orderNo;

    @Desensitized(startInclude = 5, endExclude = 18)
    private String sn;

    @Desensitized(type = DesensitizedType.CHINESE_NAME)
    private String username;

    @Desensitized(type = DesensitizedType.MOBILE_PHONE)
    private String mobile;

    @Desensitized(type = DesensitizedType.ID_CARD)
    private String idCard;
}
