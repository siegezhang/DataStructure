package feature.java8.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class FilesTest {
  @Test
  public void testFiles() throws IOException {
    AtomicInteger fileInteger = new AtomicInteger();
    AtomicInteger dirInteger = new AtomicInteger();
    Files.walkFileTree(
        Path.of("D:\\tmp"),
        new SimpleFileVisitor<>() {
          @Override
          public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
              throws IOException {
            fileInteger.incrementAndGet();
            System.out.println(dir);
            return super.preVisitDirectory(dir, attrs);
          }

          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
              throws IOException {
            dirInteger.incrementAndGet();
            System.out.println(file);
            return super.visitFile(file, attrs);
          }
        });
    System.out.println(dirInteger);
    System.out.println(fileInteger);
  }

  @Test
  public void testFiles2() throws IOException {
    Files.walk(Path.of("D:\\tmp")).forEach(System.out::println);
  }
}
