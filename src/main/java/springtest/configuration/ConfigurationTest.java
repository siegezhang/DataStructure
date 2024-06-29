package springtest.configuration;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
  @Test
  public void test() {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ConfigBean.class);
    for (String beanName : context.getBeanDefinitionNames()) {
      String[] aliases = context.getAliases(beanName);
      System.out.println(
          String.format(
              "bean名称:%s,别名:%s,bean对象:%s",
              beanName, Arrays.asList(aliases), context.getBean(beanName)));
    }
  }

  @Test
  public void test1() {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ConfigBean1.class);
    for (String beanName : context.getBeanDefinitionNames()) {
      String[] aliases = context.getAliases(beanName);
      System.out.println(
          String.format(
              "bean名称:%s,别名:%s,bean对象:%s",
              beanName, Arrays.asList(aliases), context.getBean(beanName)));
    }
  }
}
