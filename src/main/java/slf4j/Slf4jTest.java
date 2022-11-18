package slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Slf4jTest {
  @Test
  public void testFactory() {
    Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
    logger.info("hello,{}", new Date());
  }
}
