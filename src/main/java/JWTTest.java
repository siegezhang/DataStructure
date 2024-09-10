import cn.hutool.jwt.JWT;

import java.util.Date;

public class JWTTest {

    static String ak = "2ZDIKhlGCWbXk67WdDoWPwBcwJz"; // 填写您的ak
    static String sk = "147852"; // 填写您的sk

    public static void main(String[] args) {
        String token = sign(ak, sk);
        System.out.println(token); // 打印生成的API_TOKEN
    }

    static String sign(String ak, String sk) {
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + 1800 * 1000);
            Date notBefore = new Date(System.currentTimeMillis() - 5 * 1000);
            byte[] key = sk.getBytes();
            return JWT.create()
                    .setKey(key)
                    //  .setPayload("iss", ak)
                      .setPayload("id", "7b3eef78-f8fa-4df8-a2bd-fe3162750ee8")
                      .setPayload("userName", "admin")
                    .setPayload("exp", expiredAt)
                    .setPayload("iat", notBefore)
                    .sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String sign1(String ak, String sk) {
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + 1800 * 1000);
            Date notBefore = new Date(System.currentTimeMillis() - 5 * 1000);
            byte[] key = sk.getBytes();
            return JWT.create()
                    .setKey(key)
                    .setPayload("iss", ak)
                    .setPayload("exp", expiredAt)
                    .setPayload("nbf", notBefore)
                    .sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

//输出内容为：eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIyWkRJS2hsR0NXYlhrNjdXZERvV1B3QmN3SnoiLCJleHAiOjE3MDkyMDY4ODUsIm5iZiI6MTcwOTIwNTA4MH0.AmQxbpLRuMxoR3bM9Lbvavs6CBxCNt5x3GHqQo2SLAc