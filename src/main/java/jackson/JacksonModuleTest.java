package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonModuleTest {
  @Test
  public void test() {
    ObjectMapper mapper = new ObjectMapper();
    ObjectMapper andRegisterModules = mapper.findAndRegisterModules();
    System.out.println(andRegisterModules);
  }
}
