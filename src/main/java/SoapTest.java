import cn.hutool.http.webservice.SoapProtocol;
import cn.hutool.http.webservice.SoapUtil;

public class SoapTest {
    public static void main(String[] args) {
        String result = SoapUtil.createClient(url, SoapProtocol.SOAP_1_1)
                .header("SOAPAction", action)
                .setMethod(method, namespace)
                .setParam("param1", 1, false)
                .setParam("param2", 2, false)
                .setParam("param3", 3, false)
                .setParam("param4", 4, false).send();
    }
}
