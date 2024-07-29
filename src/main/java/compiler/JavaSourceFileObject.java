package compiler;

import java.io.IOException;
import java.net.URI;
import javax.tools.SimpleJavaFileObject;

/** 待编译对象 存储待编译的字符串 */
public class JavaSourceFileObject extends SimpleJavaFileObject {

  // 表示java源代码
  private CharSequence content;

  protected JavaSourceFileObject(String className, String content) {
    super(
        URI.create("string:///" + className.replaceAll("\\.", "/") + Kind.SOURCE.extension),
        Kind.SOURCE);
    this.content = content;
  }

  /**
   * 获取需要编译的源代码
   *
   * @param ignoreEncodingErrors
   * @return
   * @throws IOException
   */
  @Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    return content;
  }
}
