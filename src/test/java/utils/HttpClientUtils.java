package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;


public class HttpClientUtils {



    public static String get(String url, Map<String, String> params) throws URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        params.forEach((key, value) -> {
            uriBuilder.setParameter(key, value);
        });
        HttpGet httpget = new HttpGet(uriBuilder.build());
        String content = null;
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            if (response.getStatusLine().getStatusCode() == 200) {
//                content = JSON.toJSONString(response.getEntity());
                content = EntityUtils.toString(response.getEntity(), "utf-8");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}