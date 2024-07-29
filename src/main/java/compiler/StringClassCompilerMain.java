package compiler;

import java.net.URISyntaxException;
import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class StringClassCompilerMain {
  public static void main(String[] args) {
    JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager standardJavaFileManager =
        javaCompiler.getStandardFileManager(null, null, null);
    JavaFileObject testFile = generateTest();
    Iterable<? extends JavaFileObject> classes = Arrays.asList(testFile);
    JavaCompiler.CompilationTask task =
        javaCompiler.getTask(null, standardJavaFileManager, null, null, null, classes);
    if (task.call()) {
      System.out.println("success");
    } else {
      System.out.println("failure!");
    }
  }

  // 通过字符串创建一个待编译对象
  private static JavaFileObject generateTest() {
    String contents =
        "package compiler;"
            + "class Test {\n"
            + "  public static void main(String[] args) {\n"
            + "    System.out.println(\"success\");\n"
            + "  }\n"
            + "}\n";
    StringObject so = null;
    try {
      so = new StringObject("compiler.Test", contents);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return so;
  }
}
