package feature.java21;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import static java.lang.StringTemplate.RAW;
import static java.util.FormatProcessor.FMT;
import java.time.format.DateTimeFormatter;
public class Java21Test {
  StringTemplate.Processor<JSONObject, RuntimeException> JSON
          = StringTemplate.Processor.of(
          (StringTemplate st) -> JSONObject.parseObject(st.interpolate())
  );
  /**
   * New String Methods
   * <li>String.indexOf(String str, int beginIndex, int endIndex) – searches the specified substring
   *     in a subrange of the string.
   * <li>String.indexOf(char ch, int beginIndex, int endIndex) – searches the specified character in
   *     a subrange of the string.
   * <li>String.splitWithDelimiters(String regex, int limit) – splits the string at substrings
   *     matched by the regular expression and returns an array of all parts and splitting strings.
   *     The string is split at most limit-1 times, i.e., the last element of the array could be
   *     further divisible.
   */
  @Test
  public void test1() {
    String string = "the red brown fox jumps over the lazy dog";
    String[] parts = string.splitWithDelimiters(" ", 5);
    System.out.println(Arrays.stream(parts).collect(Collectors.joining("', '", "'", "'")));
  }

  /**
   * New StringBuilder and StringBuffer Methods
   * <li>repeat(CharSequence cs, int count) – appends to the StringBuilder or StringBuffer the
   *     string cs – count times.
   * <li>repeat(int codePoint, int count) – appends the specified Unicode code point to the
   *     StringBuilder or StringBuffer – count times. A variable or constant of type char can also
   *     be passed as code point.
   */
  @Test
  public void test2() {
    StringBuilder sb = new StringBuilder();
    sb.repeat("Hello ", 2);
    sb.repeat(0x1f600, 5);
    sb.repeat('!', 3);
    System.out.println(sb);
  }

  /**
   * New Character Methods
   * <li>isEmoji(int codePoint)
   * <li>isEmojiComponent(int codePoint)
   * <li>isEmojiModifier(int codePoint)
   * <li>isEmojiModifierBase(int codePoint)
   * <li>isEmojiPresentation(int codePoint)
   * <li>isExtendedPictographic(int codePoint)
   */
  @Test
  public void test3() {
    System.out.println(Character.isEmoji(0x1f600));
  }

  /**
   * New Math Methods
   * <li>int clamp(long value, int min, int max)
   * <li>long clamp(long value, long min, long max)
   * <li>double clamp(double value, double min, double max)
   * <li>float clamp(float value, float min, float max)
   */
  @Test
  public void test4() {
    System.out.println(Math.clamp(3, 1, 100));
    System.out.println(Math.clamp(0, 1, 100));
    System.out.println(Math.clamp(101, 1, 100));
  }

  /**
   * 可以自定义类似STR的Template Processor
   * A template processor defines how template text and placeholders are combined.
   * 可以参考scala中对于字符串模板的处理
   * <li>STR: This processor takes care of standard interpolation, making it a versatile choice for string manipulation.
   *<li> FMT: Unlike its counterparts, FMT not only performs interpolation but also excels at interpreting format specifiers located to the left of embedded expressions. These format specifiers are well-documented within Java’s Formatter class.
   * <li>RAW: RAW stands as a steadfast template processor, primarily generating unprocessed StringTemplate objects.
   */
  @Test
  public void test5() {
    int    httpStatus   = 200;
    String errorMessage = "success";
    String json = STR."""
    {
      "httpStatus": \{httpStatus},
      "errorMessage": "\{errorMessage}"
    }""";
    System.out.println(json);

    int x = 10, y = 20;
    String s = STR."\{x} + \{y} = \{x + y}";
    System.out.println(s);

    StringTemplate st = RAW."\{x} + \{y} = \{x + y}";
    List<String> fragments = st.fragments();
    List<Object> values = st.values();

    StringTemplate st1 = RAW."我的\n世界";
    System.out.println(STR.process(st));
    System.out.println(STR.process(st1));
    DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    System.out.println(STR."The current date is: \{DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now())}");

  }
  /**
   * Unnamed Patterns and Variables
   */
  @Test
  public void test6() {
    try {
      int number = Integer.parseInt("asd");
   // } catch (NumberFormatException _) {
    } catch (NumberFormatException e) {
      System.err.println("Not a number");
    }
  }

  @Test
  public void test7() {
    Object obj=12;
    switch (obj) {
      case Byte    _ -> System.out.println("Integer number");
      case Short   _ -> System.out.println("Integer number");
      case Integer _ -> System.out.println("Integer number");
      case Long    _ -> System.out.println("Integer number");

      case Float  _ -> System.out.println("Floating point number");
      case Double _ -> System.out.println("Floating point number");

      default -> System.out.println("Not a number");
    }

    switch (obj) {
      case Byte _, Short _, Integer _, Long _ -> System.out.println("Integer number");
      case Float _, Double _                  -> System.out.println("Floating point number");

      default -> System.out.println("Not a number");
    }
  }

  @Test
  public void test8() {
    var list= List.of("a", "b", "c", "d");
    var last = list.getLast();
    var linkedHashSet= LinkedHashSet.newLinkedHashSet(3);
    linkedHashSet.add("a");
    linkedHashSet.add("b");
    linkedHashSet.add("c");
    var first = linkedHashSet.getFirst();
  }

  record Rectangle(String name, double width, double height) {
    double area() {
      return width * height;
    }
  }
  Rectangle[] zone = new Rectangle[] {
          new Rectangle("Alfa", 17.8, 31.4),
          new Rectangle("Bravo", 9.6, 12.4),
          new Rectangle("Charlie", 7.1, 11.23),
  };
  @Test
  public void test9() {
    String table = STR."""
    Description  Width  Height  Area
    \{zone[0].name}  \{zone[0].width}  \{zone[0].height}     \{zone[0].area()}
    \{zone[1].name}  \{zone[1].width}  \{zone[1].height}     \{zone[1].area()}
    \{zone[2].name}  \{zone[2].width}  \{zone[2].height}     \{zone[2].area()}
    Total \{zone[0].area() + zone[1].area() + zone[2].area()}
    """;
    System.out.println(table);

    String table1 = FMT."""
    Description     Width    Height     Area
    %-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
    %-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
    %-12s\{zone[2].name}  %7.2f\{zone[2].width}  %7.2f\{zone[2].height}     %7.2f\{zone[2].area()}
    \{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area() + zone[2].area()}
    """;
    System.out.println(table1);

  }

  /**
   * 内插字符串通过插件来实现的
   * https://github.com/122006/Zircon/blob/master/mds/README_ZrString.md
   */
  @Test
  public void test10(){
//    String text=f" this is F-$String.class.getSimpleName() ";
//    assert Objects.equals($"Zircon: [ ${text.trim()} ]","Zircon: [ "+text.trim()+" ]");
  }
  @Test
  public void test11(){


    String name    = "Joan Smith";
    String phone   = "555-123-4567";
    String address = "1 Maple Drive, Anytown";
    JSONObject doc = JSON."""
    {
        "name":    "\{name}",
        "phone":   "\{phone}",
        "address": "\{address}"
    }
    """;
    System.out.println(doc);
  }

}
