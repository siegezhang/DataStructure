package jvmlearning.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class MyAgent {
  public static void agentmain(String args, Instrumentation instrumentation) {
    System.out.println("agentmain");
    instrumentation.addTransformer(new MyTransformer());
  }

  public static void premain(String args, Instrumentation instrumentation) {
    System.out.println("premain");
    instrumentation.addTransformer(new MyTransformer());
  }

  static class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(
        ClassLoader loader,
        String className,
        Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain,
        byte[] classfileBuffer)
        throws IllegalClassFormatException {
      System.out.printf(
          "Loaded %s:0x%X%X%X%X\n",
          className,
          classfileBuffer[0],
          classfileBuffer[1],
          classfileBuffer[2],
          classfileBuffer[3]);
      return null;
    }
  }
}
