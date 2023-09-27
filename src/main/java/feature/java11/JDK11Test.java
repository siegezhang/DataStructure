package feature.java11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class JDK11Test {

  @Test
  public void test1() throws IOException {
    System.out.println("test\nhoge\n".lines().map(String::toUpperCase).toArray());
    System.out.println("test".repeat(3));
    var halfSpace = "\u0020";
    System.out.println(halfSpace.isBlank());
    var fullSpace = "\u3000";
    System.out.println(fullSpace.isBlank());
    var aaa = fullSpace + "aaa" + fullSpace;
    System.out.println(aaa.strip());
    System.out.println(aaa.trim());
    System.out.println(Character.toString(65));
    Files.writeString(Path.of("text.txt"), "hello");
    // Files.delete(Path.of("text.txt"));
    Files.readString(Path.of("text.txt"));
    System.out.println(Stream.of("aa", "", "bb").filter(Predicate.not(String::isEmpty)).toArray());
    System.out.println(List.of("aa", "bb").toArray(String[]::new));
    System.out.println(Optional.ofNullable(null).isEmpty());
  }

  @Test
  public void test2() throws IOException, InterruptedException {
    HttpClient client =
        HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(3))
            .build();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.baidu.com")).build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.statusCode());
    System.out.println(response.body());
  }

  @Test
  public void test3() {
    var product =
        new Object() {
          String name = "Apple";
          int total = 30;
        };
    System.out.println("name=" + product.name + ",total=" + product.total);
  }

  @Test
  public void test4() {
  }
}
