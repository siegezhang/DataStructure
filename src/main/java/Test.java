import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Test {
  public static void main(String[] args) throws UnsupportedEncodingException {
    String mytest = System.getProperty("org.glassfish.web.rfc2109_cookie_names_enforced");
    System.out.println(mytest);
    System.out.println(new String(mytest.getBytes("GBK")));
    byte[] bytes1 = "阿".getBytes(StandardCharsets.UTF_8);
    byte[] bytes2 = "阿".getBytes("GBK");
    System.out.println(System.getProperty("file.encoding"));
    System.out.println("你好");
  }
}
