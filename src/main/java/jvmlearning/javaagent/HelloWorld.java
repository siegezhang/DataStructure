package jvmlearning.javaagent;

public class HelloWorld {
  public static void main(String[] args) throws InterruptedException {
    for (;;){
      Thread thread=new Thread();
      thread.sleep(10000);
      System.out.println("HelloWorld");
    }
  }
}
