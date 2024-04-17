package validator;

/**
 * 数据状态枚举
 *
 * @author xiaosheng.zhang
 * @date 20201221
 */
public enum YesNoEnum {

  /** 是 */
  YES(1, "1"),

  /** 否 */
  NO(0, "2"),

  /** 否 */
  SUBMIT(2, "3"),
  ;

  public final int status;
  public final String desc;

  YesNoEnum(int status, String desc) {
    this.desc = desc;
    this.status = status;
  }

  //  @JsonValue
  //  public static YesNoEnum1 getEnum(int status) {
  //    for (YesNoEnum1 enum1 : YesNoEnum1.values()) {
  //      if (enum1.status == status) {
  //        return enum1;
  //      }
  //    }
  //    return null;
  //  }
}
