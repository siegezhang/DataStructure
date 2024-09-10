package spoon.test;

public enum BizLockEnum {

    ADD_APPOINTMENT("ADD_APPOINTMENT", "添加预约"),
    /* 添加城市 */
    ADD_CITY("ADD_CITY", "添加城市"),
    ;

    public final java.lang.String code;

    public final java.lang.String desc;

    BizLockEnum(java.lang.String code, java.lang.String desc) {
        this.code = code;
        this.desc = desc;
    }
}
