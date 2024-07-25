package annotation.desensitized.another2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Objects;

public class SensitiveJsonSerializer extends JsonSerializer<String>
    implements ContextualSerializer {
  private SensitiveStrategy strategy;

  // 进行序列化
  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(strategy.serialize().apply(value));
  }

  // 获取属性上的注解属性
  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
      throws JsonMappingException {
    Sensitive annotation = property.getAnnotation(Sensitive.class);
    if (Objects.nonNull(annotation)
        && Objects.equals(String.class, property.getType().getRawClass())) {
      this.strategy = annotation.strategy();
      return this;
    }
    return prov.findValueSerializer(property.getType(), property);
  }
}
