package bytecode;

import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistExample {
  public static void main(String[] args) throws Exception {
    ClassPool pool = ClassPool.getDefault();
    CtClass targetClass = pool.get("bytecode.TargetClass");
    CtMethod targetMethod = targetClass.getDeclaredMethod("execute");
    // 在方法调用前记录开始时间
    //  targetMethod.insertBefore("long startTime = System.currentTimeMillis();");
    CtBehavior ctBehavior =
        new CtBehavior(targetClass, targetMethod.getMethodInfo()) {
          @Override
          public String getName() {
            return "";
          }

          @Override
          public String getLongName() {
            return "";
          }

          @Override
          public boolean isEmpty() {
            return false;
          }
        };
    //    targetMethod.insertAfter(
    //        "System.out.println(\"Execution time: \" + (System.currentTimeMillis() - startTime) +
    // \" ms\");");
    ctBehavior.addLocalVariable("startTime", CtClass.longType);
    ctBehavior.insertBefore("long startTime = System.currentTimeMillis();");
    ctBehavior.insertAfter(
        "System.out.println(\"Execution time: \" + (System.currentTimeMillis() - startTime) + \" ms\");");

    // 转换并加载修改后的类
    Class<?> modifiedClass = targetClass.toClass();
    targetClass.detach();

    // 创建目标类实例并调用方法
    Object instance = modifiedClass.newInstance();
    modifiedClass.getMethod("execute").invoke(instance);
  }
}
