package shufa;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShufaUtils {
    @Test
    public void getImg() throws IOException {
        HttpClient client= HttpClients.createDefault();
        HttpPost post=new HttpPost("http://www.shufazidian.com/");

        List<NameValuePair> list=new ArrayList<>();
        list.add(new BasicNameValuePair("wd","请"));
        list.add(new BasicNameValuePair("sort","8"));

        post.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));

        HttpResponse resp=client.execute(post);
        HttpEntity entity=resp.getEntity();
        String content=EntityUtils.toString(entity);
       // System.out.println(content);

        EntityUtils.consume(entity);
        //System.out.println(entity);
        parseHtml(content);
    }

    private void parseHtml(String html){
        Document doc = Jsoup.parse(html);
        Elements eles=doc.getElementsByClass("mbpho");
        for (Element e:eles) {
            Elements imgs=e.getElementsByTag("img");
            for (Element img:imgs) {
                if (img.attr("src").indexOf("http")==-1)
                    System.out.println("<img src=\""+"http://www.sfzd.cn/"+img.attr("src")+"\">");
                else
                    System.out.println("<img src=\""+img.attr("src")+"\">");
                ;
            }
        }
    }
}

