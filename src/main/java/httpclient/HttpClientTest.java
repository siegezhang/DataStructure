package httpclient;

import java.io.FileNotFoundException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class HttpClientTest {

  @Test
  public void test1() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://openjdk.org/")).build();
    client
        .sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();
  }

  @Test
  public void test2() {
    HttpClient client =
        HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
            .authenticator(Authenticator.getDefault())
            .build();
  }

  @Test
  public void test3() throws FileNotFoundException {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create("http://openjdk.org/"))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
            .build();
  }
}
