package java8.extend;

import com.google.common.collect.Streams;
import lombok.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Stream;

public class GuavaStreamTest {
  @Test
  public void test() {
    Stream<Person> s1 =
        Stream.of(
            new Person(1, "John", "Smith"),
            new Person(2, "Tom", "Hamilton"),
            new Person(3, "Paul", "Walker"));
    Stream<PersonAddress> s2 =
        Stream.of(
            new PersonAddress(1, "London", "Street1", "100"),
            new PersonAddress(2, "Manchester", "Street1", "101"),
            new PersonAddress(3, "London", "Street2", "200"));
    Stream<PersonDTO> s3 =
        Streams.zip(
            s1,
            s2,
            (p, pa) ->
                PersonDTO.builder()
                    .id(p.getId())
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .city(pa.getCity())
                    .street(pa.getStreet())
                    .houseNo(pa.getHouseNo())
                    .build());
    s3.forEach(
        dto -> {
          Assertions.assertNotNull(dto.getId());
          Assertions.assertNotNull(dto.getFirstName());
          Assertions.assertNotNull(dto.getCity());
        });
  }

  @Test
  public void test1() {
    Stream<Integer> s1 = Stream.of(1, 2, 3);
    Stream<Integer> s2 = Stream.of(4, 5, 6);
    Stream<Integer> s3 = Stream.of(7, 8, 9);
    Stream<Integer> s4 = Streams.concat(s1, s2, s3);
    Assertions.assertEquals(9, s4.count());
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Person {
    private Integer id;
    private String firstName;
    private String lastName;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class PersonAddress {
    private Integer id;
    private String city;
    private String street;
    private String houseNo;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class PersonDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String houseNo;
  }
}
