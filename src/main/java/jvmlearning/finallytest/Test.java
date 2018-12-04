package jvmlearning.finallytest;

import java.util.HashMap;
import java.util.Map;

public class Test {
  public static void main(String[] args) {
    // System.out.println("return value of test1(): " + test1());
    // System.out.println("return value of test2(): " + test2());
    // System.out.println("return value of test3(): " + test3());
    //System.out.println("return value of test4(): " + test4());
   // System.out.println("return value of test5(): " + test5());
    System.out.println("return value of test7(): " + test7());
  }

  private static int test1() {
    int i = 1;
    try {
      System.out.println("try block");
      System.exit(0);
      return i;
    } finally {
      System.out.println("finally block");
    }
  }

  private static int test2() {
    try {
      return 1;
    } finally {
      return 2;
    }
  }

  private static int test3() {
    int i = 1;
    try {
      return i;
    } finally {
      i++;
    }
  }

  private static int test4() {
    int i = 1;
    try {
      i = 4;
    } finally {
      i++;
      return i;
    }
  }

  private static String test5() {
    try {
      System.out.println("try block");
      return test6();
    } finally {
      System.out.println("finally block");
    }
  }

  public static String test6() {
    System.out.println("return statement");
    return "after return";
  }

  public static Map<String, String> test7() {
      Map<String, String> map = new HashMap<String, String>();
      map.put("KEY", "INIT");
      try {
          map.put("KEY", "TRY");
          return map;
      }
      catch (Exception e) {
          map.put("KEY", "CATCH");
      }
      finally {
          map.put("KEY", "FINALLY");
          map = null;
      }

      return map;
  }
}
