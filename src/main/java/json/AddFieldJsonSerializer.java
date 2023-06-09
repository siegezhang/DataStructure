package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.lang.reflect.Field;

public class AddFieldJsonSerializer extends JsonSerializer<String> {

  @Override
  public void serialize(String name, JsonGenerator gen, SerializerProvider serializerProvider)
      throws IOException {
    Object currentValue = gen.getCurrentValue();
    String fieldName = gen.getOutputContext().getCurrentName();
    try {
      Field field = currentValue.getClass().getDeclaredField(fieldName);
      TestAnno annotation = field.getAnnotation(TestAnno.class);
      gen.writeString(name);
      gen.writeFieldName(annotation.field());
      gen.writeString(name + "__");
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Person user = new Person("zhangsan", System.currentTimeMillis());
    String userStr = objectMapper.writeValueAsString(user);
    System.out.println(userStr);
  }
}
