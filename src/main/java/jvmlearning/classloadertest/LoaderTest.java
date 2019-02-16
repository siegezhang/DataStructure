package jvmlearning.classloadertest;

import org.junit.jupiter.api.Test;

public class LoaderTest {
  @Test
  public void test1() {
    System.out.println(LoaderTest.class.getClassLoader());
    System.out.println(LoaderTest.class.getClassLoader().getParent());
    System.out.println(LoaderTest.class.getClassLoader().getParent().getParent());
  }
}
