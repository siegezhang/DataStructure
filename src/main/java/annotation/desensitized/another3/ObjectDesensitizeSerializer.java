package annotation.desensitized.another3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class ObjectDesensitizeSerializer extends StdSerializer<Object>
    implements ContextualSerializer {

  private static final long serialVersionUID = -7868746622368564541L;
  private transient Desensitization<Object> desensitization;

  protected ObjectDesensitizeSerializer() {
    super(Object.class);
  }

  public Desensitization<Object> getDesensitization() {
    return desensitization;
  }

  public void setDesensitization(Desensitization<Object> desensitization) {
    this.desensitization = desensitization;
  }

  @Override
  public JsonSerializer<Object> createContextual(SerializerProvider prov, BeanProperty property) {
    // 获取属性注解
    Desensitize annotation = property.getAnnotation(Desensitize.class);
    return createContextual(annotation.desensitization());
  }

  @SuppressWarnings("unchecked")
  public JsonSerializer<Object> createContextual(Class<? extends Desensitization<?>> clazz) {
    ObjectDesensitizeSerializer serializer = new ObjectDesensitizeSerializer();
    if (clazz != DefaultDesensitization.class) {
      serializer.setDesensitization(
          (Desensitization<Object>) DesensitizationFactory.getDesensitization(clazz));
    }
    return serializer;
  }

  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    Desensitization<Object> objectDesensitization = getDesensitization();
    if (objectDesensitization != null) {
      try {
        gen.writeObject(objectDesensitization.desensitize(value));
      } catch (Exception e) {
        gen.writeObject(value);
      }
    } else if (value instanceof String) {
      gen.writeString(Symbol.getSymbol(((String) value).length(), Symbol.STAR));
    } else {
      gen.writeObject(value);
    }
  }
}
