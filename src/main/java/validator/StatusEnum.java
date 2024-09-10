package validator;

public enum StatusEnum {
    VALID(1, "VALID"),

    INVALID(0, "INVALID"),
    ;

    StatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private final int status;
    private final String desc;
}
