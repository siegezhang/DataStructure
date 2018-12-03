package jvmlearning.jni;

public class Foo {
    public static native void foo();
    public native void bar(int i,long j);
    public native void bar(String s,Object o);

    int i=0xDEADBEEF;
  public static void main(String[] args) {
    System.loadLibrary("JniTest");
    new Foo().bar("","");
  }
}
