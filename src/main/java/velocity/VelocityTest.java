package velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.DisplayTool;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.*;

public class VelocityTest {
  @Test
  public void testVelocity1() {
    /* 首先，初始化运行时引擎，使用默认的配置 */
    Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    Velocity.setProperty(
        "classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    Velocity.init();
    /* 创建Context对象，然后把数据放进去 */
    VelocityContext context = new VelocityContext();
    context.put("name", "Velocity");
    context.put("project", "Jakarta");
    /* 渲染模板 */
    StringWriter w = new StringWriter();
    Velocity.mergeTemplate("velocity/testtemplate.vm", "UTF-8", context, w);
    System.out.println("template:" + w);
    /* 渲染字符串 */
    String s = "We are using $project $name to render this.";
    w = new StringWriter();
    Velocity.evaluate(context, w, "mystring", s);
    System.out.println(" string : " + w);
  }

  @Test
  public void testVelocity2() {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    Template t = ve.getTemplate("velocity/hellovelocity.vm");
    VelocityContext ctx = new VelocityContext();
    ctx.put("display", new DisplayTool());
    ctx.put("name", "velocity");
    Hello test = new Hello();
    test.setHello("hello");
    ctx.put("test1", test);
    ctx.put("date", (new Date()).toString());
    List<String> temp = new ArrayList<>();
    temp.add("1");
    temp.add("2");
    ctx.put("list", temp);
    StringWriter sw = new StringWriter();
    t.merge(ctx, sw);
    System.out.println(sw);
  }

  @Test
  public void testVelocity3() {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    Template t = ve.getTemplate("velocity/test_list.vm");
    VelocityContext ctx = new VelocityContext();
    ctx.put("display", new DisplayTool());
    ctx.put("name", "velocity");
    List<String> temp = new ArrayList<>();
    temp.add("test_1");
    temp.add("test_2");
    temp.add("test_3");
    temp.add("test_4");
    ctx.put("list", temp);
    StringWriter sw = new StringWriter();
    t.merge(ctx, sw);
    System.out.println(sw);
  }

  @Test
  public void testVelocity4() {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    Template t = ve.getTemplate("velocity/test_map.vm");
    VelocityContext ctx = new VelocityContext();
    ctx.put("display", new DisplayTool());
    ctx.put("name", "velocity");
    Map<String, String> map = new HashMap<>();
    map.put("string", "java.lang.String");
    map.put("int", "java.lang.Integer");
    ctx.put("java8/map", map);
    List<String> list = new ArrayList<>();
    list.add("string");
    list.add("int");
    ctx.put("list", list);
    List<String> jdbcList = new ArrayList<>();
    jdbcList.add("Long");
    jdbcList.add("String");
    ctx.put("jdbcList", jdbcList);
    StringWriter sw = new StringWriter();
    t.merge(ctx, sw);
    System.out.println(sw);
  }

  @Test
  public void testVelocity5() {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    Template t = ve.getTemplate("velocity/test_contain.vm");
    VelocityContext ctx = new VelocityContext();
    ctx.put("display", new DisplayTool());
    ctx.put("name", "velocity");
    List<String> list = new ArrayList<>();
    list.add("string");
    list.add("int");
    list.add("modifyTime");
    list.add("updateDate");
    ctx.put("list", list);
    StringWriter sw = new StringWriter();
    t.merge(ctx, sw);
    System.out.println(sw);
  }
}
