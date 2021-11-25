package jks;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 在对应目录下执行下面命令:
 * <p>
 * keytool -genkey -alias jwt -keyalg RSA -keysize 1024 -keystore jwt.jks -validity 365
 */

public class JksTest {

    public static final String JKS_JWT_PATH = "jks\\jwt.jks";
    public static final String PASSWORD = "123456";
    public static final String ALIAS = "jwt";

    @Test
    public void testJks() throws Exception {
        PrivateKey privateKey = getPrivateKey(JKS_JWT_PATH, PASSWORD, ALIAS);
        PublicKey publicKey = getPublicKey(JKS_JWT_PATH, PASSWORD, ALIAS);
        System.out.println("privateKey:" + privateKey + "\n" + "publicKey:" + publicKey);
    }

    private PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, password.toCharArray());
        return (PrivateKey) keyStore.getKey("jwt", password.toCharArray());
    }

    private PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, password.toCharArray());
        return keyStore.getCertificate("jwt").getPublicKey();

    }

}
