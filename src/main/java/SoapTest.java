import cn.hutool.core.lang.Console;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.http.webservice.SoapProtocol;
import cn.hutool.http.webservice.SoapUtil;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SoapTest {
  public static void main(String[] args) throws SOAPException, IOException {
    SoapClient client =
        SoapUtil.createClient("http://localhost:8081/soap/personalService", SoapProtocol.SOAP_1_1)
            .setMethod("ws:patientSearch", "http://ws.gateway.sdc.gsww.com/")
            .setParam(
                "text",
                "<![CDATA[<root><request><YLJGDM>620000000718</YLJGDM><ZJLX>01</ZJLX><VUID/><CHILDREN/><ZJHM>620302194209050010</ZJHM><XM>付世存</XM></request></root>]]>",
                false);
    System.out.println(client.getMsgStr(true));
    System.out.println("###############");
    //System.out.println(client.send());
    SOAPMessage message = client.sendForMessage();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    message.writeTo(out);
    System.out.println(out);
    System.out.println("###############");
    Console.log(message.getSOAPBody().getTextContent());
  }
}
