package annotation.desensitized.another3;

public interface Desensitization<T> {
  /**
   * 脱敏实现
   *
   * @param target 脱敏对象
   * @return 脱敏返回结果
   */
  T desensitize(T target);
}
