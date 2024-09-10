package validator;

import lombok.Data;

@Data
public class User1 {

    @EnumValid(enumClass = StatusEnum.class,field = "status")
    private int status;

    private String name;


}
