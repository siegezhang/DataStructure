package jvmlearning.classloadertest;

import org.junit.Test;

public class LoaderTest {
  @Test
  public void test1() {
    System.out.println(LoaderTest.class.getClassLoader());
    System.out.println(LoaderTest.class.getClassLoader().getParent());
    System.out.println(LoaderTest.class.getClassLoader().getParent().getParent());
  }
}
