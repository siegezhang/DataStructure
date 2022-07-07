package kettle;

import org.junit.jupiter.api.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.encryption.Encr;
import org.pentaho.di.core.exception.KettleException;

/***
 * 注意的jar包仓库位置，从mvnrepository可以得知只存在https://nexus.pentaho.org/content/groups/omni/中，需要从该处拉取
 */
public class KettleTest {
  @Test
  public void testKettle() {
    try {
      KettleEnvironment.init();
      String pwd = "Encrypted 2be98afc800e48b889f11a7639dba86e9";
      String pwd1 = "2be98afc800e48b889f11a7639dba86e9";
      System.out.println("before:" + pwd);
      String decryptPassword = Encr.decryptPassword(pwd);
      String decryptPassword1 = Encr.decryptPassword(pwd1);
      System.out.println("after:" + decryptPassword);
      System.out.println("after:" + decryptPassword1);
    } catch (KettleException e) {
      e.printStackTrace();
    }
  }
}
