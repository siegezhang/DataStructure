package jdk17;

/**
 * Record被用来设计传输不可变的数据。从上面的例子可以看到，一个Record类被初始化后里面的属性是不能改变的，
 * 没有Setter方法而是通过全参数构造来初始化数据，天然线程安全。
 * 所有用Record关键字声明的类都是java.lang.Record的子类，这一点有点像枚举
 * 如何判断一个类是Record类：
 * 1.Record.class.isAssignableFrom(MyRecord.class)
 * 2.MyRecord.class.isRecord()
 * Class类还提供了getRecordComponents来获取Record类的成员属性信息：
 * RecordComponent[] recordComponents = MyRecord.class.getRecordComponents();
 */
public record MyRecord(String username, Integer age) {
}
