package bytecode;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.junit.jupiter.api.Test;

public class ByteCodeTest {
  @Test
  public void test1() {
    ClassPool pool = ClassPool.getDefault();
    CtClass newClass = pool.makeClass("bytecode.MyNewClass");
  }

  @Test
  public void test2() throws NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass existingClass = pool.get("bytecode.MyExistingClass");
    existingClass.setSuperclass(pool.get("bytecode.MySuperClass"));
  }

  @Test
  public void test3() throws NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass existingClass = pool.get("bytecode.MyExistingClass");
    // 添加方法
    CtMethod newMethod =
        CtNewMethod.make("public int add(int a, int b) { return a + b; }", existingClass);
    existingClass.addMethod(newMethod);
    // 删除方法
    CtMethod methodToRemove = existingClass.getDeclaredMethod("methodName");
    existingClass.removeMethod(methodToRemove);
    // 修改方法
    CtMethod methodToModify = existingClass.getDeclaredMethod("methodName");
    methodToModify.setBody("{ return $1 * $1; }");

    // 添加字段
    CtField newField = new CtField(CtClass.intType, "count", existingClass);
    newField.setModifiers(Modifier.PRIVATE);
    existingClass.addField(newField);

    // 删除字段
    CtField fieldToRemove = existingClass.getField("fieldName");
    existingClass.removeField(fieldToRemove);

    // 修改字段
    CtField fieldToModify = existingClass.getField("fieldName");
    fieldToModify.setModifiers(Modifier.PUBLIC);
  }

  @Test
  public void test4() throws NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass proxyClass = pool.makeClass("com.example.MyProxy");

    // 为代理类添加接口
    proxyClass.addInterface(pool.get("com.example.MyInterface"));

    // 添加委托对象字段
    CtField delegateField =
        new CtField(pool.get("com.example.MyInterface"), "delegate", proxyClass);
    delegateField.setModifiers(Modifier.PRIVATE);
    proxyClass.addField(delegateField);

    // 为代理类的每个方法添加代理逻辑
    for (CtMethod method : pool.get("com.example.MyInterface").getDeclaredMethods()) {
      CtMethod proxyMethod = CtNewMethod.delegator(method, proxyClass);
      proxyClass.addMethod(proxyMethod);
    }
  }

  @Test
  public void test5() throws NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass targetClass = pool.get("com.example.MyClass");
    CtMethod targetMethod = targetClass.getDeclaredMethod("myMethod");

    // 在方法调用前插入逻辑
    targetMethod.insertBefore("System.out.println(\"Before method call\");");

    // 在方法调用后插入逻辑
    targetMethod.insertAfter("System.out.println(\"After method call\");");
  }

  @Test
  public void test6() throws NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass targetClass = pool.get("com.example.MyClass");
    CtMethod targetMethod = targetClass.getDeclaredMethod("myMethod");

    // 在方法调用前注入代码
    targetMethod.instrument(
        new ExprEditor() {
          @Override
          public void edit(MethodCall m) throws CannotCompileException {
            m.replace("System.out.println(\"Before method call: \" + $1); $_ = $proceed($$);");
          }
        });
  }
}
