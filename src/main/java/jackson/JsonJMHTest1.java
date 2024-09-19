package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 3, time = 1)
public class JsonJMHTest1 {

  String json = "{\"id\":122345667,\"email\":\"jianzh5@163.com\",\"price\":12.25}";
  UserEntity userEntity = new UserEntity(13345L, "jianzh5@163.com", BigDecimal.valueOf(12.25));

  /** 测试String to Object */
  @Benchmark
  public UserEntity objectMapper2ObjTest() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();
    return objectMapper.readValue(json, UserEntity.class);
  }

  /** 测试Object to String */
  @Benchmark
  public String objectMapper2StringTest() throws JsonProcessingException {
    ObjectMapper objectMapper = ObjectMapperInstance.INSTANCE.getObjectMapper();
    return objectMapper.writeValueAsString(userEntity);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt =
        new OptionsBuilder()
            .include(JsonJMHTest1.class.getSimpleName())
            .result("test1.json")
            .resultFormat(ResultFormatType.JSON)
            .build();
    new Runner(opt).run();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserEntity {
    private Long id;
    private String email;
    private BigDecimal price;
  }
}
