import cn.hutool.core.lang.Validator;

import java.io.IOException;
import java.util.UUID;

public class MyTest {

  public static void main(String[] args) throws IOException {
    //        for (int i = 0; i < 50; i++) {
    //
    //            System.out.println(UUID.randomUUID().toString());
    //        }
    //    AntPathMatcher antPathMatcher=new AntPathMatcher();
    //    System.out.println(antPathMatcher.match("/organization/*","/organization/620301"));
    //
    // System.out.println(antPathMatcher.match("/organization*","/organization?pageIndex=1&pageSize=500&status=1&upOrgCode="));
    //    boolean isMatch2 = antPathMatcher.match("/organization",
    // "/organization?pageIndex=1&pageSize=500&status=1&upOrgCode=");
    //
    //    System.out.println(isMatch2);
    System.out.println(Validator.isMobile("18516100450"));
  }


}
