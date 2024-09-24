package validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.util.List;

@GroupSequenceProvider(PersonGroupSequenceProvider.class)
@Getter
@Setter
@ToString
public class Person {

    @NotNull
    private String name;
    @NotNull
    @Range(min = 10, max = 40)
    private Integer age;

    @NotNull(groups = {WhenAge20And30Group.class, WhenAge30And40Group.class})
    @Size(min = 1, max = 2, groups = WhenAge20And30Group.class)
    @Size(min = 3, max = 5, groups = WhenAge30And40Group.class, message = "个数必须在3和5之间")
    private List<String> hobbies;

    /**
     * 2:拒绝
     * 3:通过
     */
    @NotNull
    @Range(min = 2, max = 3, message = "审核状态参数传递错误")
    private Integer auditStatus;
    /**
     * auditStatus=2时必填
     */
    @NotNull(groups = {AuditStatusRejectGroup.class})
    private String auditRejectReason;

    /**
     * 定义专属的业务逻辑分组
     */
    public interface WhenAge20And30Group {
    }

    public interface WhenAge30And40Group {
    }

    public interface AuditStatusRejectGroup {
    }
}
