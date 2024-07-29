package compiler;

import java.util.Arrays;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;

public class CompilerTest {
  @Test
  public void test1() {
    String filePath = "D:\\Client.java";
    JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
    int result = javaCompiler.run(null, null, null, filePath);
    System.out.println(result);
  }

  @Test
  public void test2() {
    String fullQuanlifiedFileName = "D:\\Client.java";
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
    Iterable<? extends JavaFileObject> files =
        fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fullQuanlifiedFileName));
    JavaCompiler.CompilationTask task =
        compiler.getTask(null, fileManager, null, null, null, files);
    Boolean result = task.call();
    if (result) {
      System.out.println("Succeeded");
    }
  }
}
