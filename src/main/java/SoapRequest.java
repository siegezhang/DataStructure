import java.io.ByteArrayOutputStream;
import javax.xml.soap.*;

public class SoapRequest {
  public static void main(String[] args) {
    try {
      // Create a SOAP Connection
      SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
      SOAPConnection soapConnection = soapConnectionFactory.createConnection();

      // Create the SOAP request message
      String url = "http://localhost:8081/soap/personalService";
      // Send SOAP Message to the server
      SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

      // Print the SOAP Response
      System.out.println("Response SOAP Message:");
      soapResponse.writeTo(System.out);
      soapConnection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static SOAPMessage createSOAPRequest() throws Exception {
    // Create a message factory for SOAP 1.1 protocol
    SOAPMessage soapMessage =
        MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage();

    // Get SOAP part of the message
    SOAPPart soapPart = soapMessage.getSOAPPart();

    // Define namespaces

    // Construct the SOAP envelope and body
    SOAPEnvelope envelope = soapPart.getEnvelope();
    envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
    envelope.addNamespaceDeclaration("ws", "http://ws.gateway.sdc.gsww.com/");

    SOAPBody soapBody = envelope.getBody();
    SOAPElement patientSearch = soapBody.addChildElement("patientSearch", "ws");

    // Create the text element with CDATA section
    SOAPElement text = patientSearch.addChildElement("text");
    text.addTextNode(
        "<![CDATA[<root><request><YLJGDM>620000000718</YLJGDM><ZJLX>01</ZJLX><VUID/><CHILDREN/><ZJHM>620302194209050010</ZJHM><XM>付世存</XM></request></root>]]>");

    // Save changes
    // soapMessage.saveChanges();

    // Print the request message for debugging
    System.out.println("Request SOAP Message:");
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    soapMessage.writeTo(out);
    System.out.println(out);

    return soapMessage;
  }
}
