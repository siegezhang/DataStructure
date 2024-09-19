import cn.hutool.core.lang.Console;
import cn.hutool.http.webservice.SoapClient;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFactory;

public class DemoClient {
    public static void main(String[] args) throws SOAPException {
        // 新建客户端
        SoapClient client = SoapClient.create("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx");
        // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间 .setMethod("web:getSupportCityString", "http://WebXml.com.cn/")
        client.setMethod("web:getSupportCityString", "http://WebXml.com.cn/");
        // 设置参数，此处自动添加方法的前缀：web .setParam("theRegionCode", "青海);
        client.getMethodEle().addChildElement(getPram("辽宁"));
        // 发送请求，参数true表示返回一个格式化后的XML内容
        // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
        Console.log(client.send(true));
    }
    public static SOAPElement getPram(String name) throws SOAPException {
        //实例化SOAP工厂
        SOAPFactory soapfactory = SOAPFactory.newInstance();
        //通过soap工厂创建标签 <web:theRegionCode>？</web:theRegionCode>
        SOAPElement theRegionCode = soapfactory.createElement("theRegionCode", "web", "http://WebXml.com.cn/");
        //将name中的内容保存到创建的theRegionCode标签中
        theRegionCode.setTextContent(name);
        return theRegionCode;
    }


}
