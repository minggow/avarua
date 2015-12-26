package com.mingguo.wu.account.test.ticket.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by mingguo.wu on 2015/11/26.
 */
public class HttpClientUtil {
    private static SSLClient HTTPCLIENT_INSTANCE = new SSLClient();

    public static String execute(String url/*, Map<String,String> map*/ ,String charset){
        HttpGet httpGet;
        String result = null;
        try{
            httpGet = new HttpGet(url);
//            if(map != null && map.size() > 0) {
//                //设置参数
//                List<NameValuePair> list = new ArrayList<NameValuePair>();
//                Iterator iterator = map.entrySet().iterator();
//                while(iterator.hasNext()){
//                    Entry<String,String> elem = (Entry<String, String>) iterator.next();
//                    list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
//                }
//                if(list.size() > 0){
//                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
//                    httpGet
//                    httpGet.setEntity(entity);
//                }
//            }
            HttpResponse response = HTTPCLIENT_INSTANCE.execute(httpGet);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    static class SSLClient extends DefaultHttpClient{
        public SSLClient() {
            super();
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                X509TrustManager tm = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {
                    }
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                ctx.init(null, new TrustManager[]{tm}, null);
                SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                ClientConnectionManager ccm = this.getConnectionManager();
                SchemeRegistry sr = ccm.getSchemeRegistry();
                sr.register(new Scheme("https", 443, ssf));
            } catch (Exception e) {
            }

        }
    }
}

