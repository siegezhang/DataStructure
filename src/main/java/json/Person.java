package json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
class Person {
  @JsonSerialize(using = AddFieldJsonSerializer.class, nullsUsing = AddFieldJsonSerializer.class)
  @TestAnno(value = "asdf", field = "addedField")
  private String name;

  private Long bithUtc;

  public Person(String name, Long bithUtc) {
    this.name = name;
    this.bithUtc = bithUtc;
  }
}
