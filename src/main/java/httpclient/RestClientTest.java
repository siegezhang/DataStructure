package httpclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.client.RestClient;

public class RestClientTest {

  @Test
  public void test1() {
    RestClient restClient = RestClient.create();
    String result = restClient.get().uri("https://example.com").retrieve().body(String.class);
    System.out.println(result);
  }

  @Test
  public void test2() {
    RestClient restClient = RestClient.create();
    ResponseEntity<String> result =
        restClient.get().uri("https://example.com").retrieve().toEntity(String.class);

    System.out.println("Response status: " + result.getStatusCode());
    System.out.println("Response headers: " + result.getHeaders());
    System.out.println("Contents: " + result.getBody());
  }

  @Test
  public void test3() {
    RestClient restClient = RestClient.create();
    int id = 1;
    Pet pet =
        restClient
            .get()
            .uri("https://petclinic.example.com/pets/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(Pet.class);
  }

  @Test
  public void test4() {
    RestClient restClient = RestClient.create();
    int id = 1;
    Pet pet = new Pet();
    ResponseEntity<Void> response =
        restClient
            .post()
            .uri("https://petclinic.example.com/pets/new")
            .contentType(MediaType.APPLICATION_JSON)
            .body(pet)
            .retrieve()
            .toBodilessEntity();
  }

  @Test
  public void test5() {
    RestClient restClient = RestClient.create();
    String result =
        restClient
            .get()
            .uri("https://example.com/this-url-does-not-exist")
            .retrieve()
            .onStatus(
                HttpStatusCode::is4xxClientError,
                (request, response) -> {
                  throw new RuntimeException();
                })
            .body(String.class);
  }

  @Test
  public void test6() {
    RestClient restClient = RestClient.create();
    int id = 1;
    Pet result =
        restClient
            .get()
            .uri("https://petclinic.example.com/pets/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .exchange(
                (request, response) -> {
                  if (response.getStatusCode().is4xxClientError()) {
                    throw new RuntimeException();
                  } else {
                    Pet pet = convertResponse(response);
                    return pet;
                  }
                });
  }

  @Test
  public void test7() {
    RestClient customClient =
        RestClient.builder()
            .requestFactory(new HttpComponentsClientHttpRequestFactory())
            .messageConverters(
                converters ->
                    converters.add(
                        new HttpMessageConverter() {
                          @Override
                          public List<MediaType> getSupportedMediaTypes() {
                            return List.of();
                          }

                          @Override
                          public Object read(Class clazz, HttpInputMessage inputMessage)
                              throws IOException, HttpMessageNotReadableException {
                            return null;
                          }

                          @Override
                          public void write(
                              Object o, MediaType contentType, HttpOutputMessage outputMessage)
                              throws IOException, HttpMessageNotWritableException {}

                          @Override
                          public boolean canWrite(Class clazz, MediaType mediaType) {
                            return false;
                          }

                          @Override
                          public boolean canRead(Class clazz, MediaType mediaType) {
                            return false;
                          }
                        }))
            .baseUrl("https://example.com")
            .defaultUriVariables(Map.of("variable", "foo"))
            .defaultHeader("My-Header", "Foo")
            .requestInterceptor((request, body, execution) -> null)
            .requestInitializer(request -> {})
            .build();
  }

  private Pet convertResponse(
      RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) {
    return new Pet();
  }

  public static class Pet {}
}
