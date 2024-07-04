package jvmlearning.monitor;

import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Scanner;

public class MonitorTest {
  public synchronized void test() {
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
    for (ThreadInfo threadInfo : threadInfos) {
      System.out.println("Thread Name: " + threadInfo.getThreadName());
      MonitorInfo[] monitorInfos = threadInfo.getLockedMonitors();
      for (MonitorInfo monitorInfo : monitorInfos) {
        System.out.println("Locked Monitor: " + monitorInfo);
      }
    }
    Scanner scanner = new Scanner(System.in);

    System.out.println("What is your name? ");
    String name = scanner.nextLine();

    System.out.println("How old are you? ");
    int age = scanner.nextInt();

    System.out.println("Hello, " + name + "! You are " + age + " years old.");
  }
}
