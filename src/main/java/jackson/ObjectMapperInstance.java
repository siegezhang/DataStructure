package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

@Getter
public enum ObjectMapperInstance {
  INSTANCE;

  private final ObjectMapper objectMapper;

  ObjectMapperInstance() {
    objectMapper = new ObjectMapper();
    // 注册自定义模块
    initialize();
  }

  private void initialize() {
    CustomJsonModule customJsonModule = new CustomJsonModule();
    objectMapper.registerModule(customJsonModule).registerModule(new JavaTimeModule() );
  }
}
