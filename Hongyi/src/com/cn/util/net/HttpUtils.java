package com.cn.util.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.cn.modle.Request;
import com.cn.util.JSONUtils;

public class HttpUtils {
    
    public static String mEncode = HTTP.UTF_8;
    
    public static String mUrlPath = "http://172.17.32.83:8080/ServerWeb/servlet/ServerServlet";
    
    private static int CONNECTION_TIMEOUT = 40000;
    
    private static HttpClient getHttpClient() {
        HttpParams params = new BasicHttpParams();
        // 设置一些基本参数
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, mEncode);
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpProtocolParams.setUserAgent(params,
                                        "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) " + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
        // 超时设置
        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(params, CONNECTION_TIMEOUT);
        
        ConnManagerParams.setTimeout(params, CONNECTION_TIMEOUT);
        
        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        
        // 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
        DefaultHttpClient httpClient = new DefaultHttpClient(conMgr, params);
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        return httpClient;
    }
    
    /**
     * @param path
     *            请求的服务器URL地址
     * @param encode
     *            编码格式
     * @return 将服务器端返回的数据转换成String
     */
    public static String sendPostMessage(String path, String encode) {
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(path);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity, encode);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
    
    public static String requestData(Request request) throws JSONException,
                                                     ClientProtocolException,
                                                     IOException,
                                                     Exception {
        HttpClient mHttpClient = null;
        HttpPost mHttpPost = null;
        HttpResponse response = null;
        String result = "";
            mHttpClient = getHttpClient();
            mHttpPost = new HttpPost(mUrlPath + "?type=" + request.action);
            StringEntity requestParam = new StringEntity(request.param, mEncode);
            mHttpPost.setEntity(requestParam);
            response = mHttpClient.execute(mHttpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, mEncode);
                }
            }
        return result;
    }
    
    // Post方式请求
    public static String requestByPost(String action) throws Throwable {
        // 请求的参数转换为byte数组
        String result = "";
        try {
            String params = "id=" + URLEncoder.encode("helloworld", "UTF-8")
                            + "&pwd="
                            + URLEncoder.encode("android", "UTF-8");
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(mUrlPath + "?type=" + action);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(5 * 1000);
            // Post请求必须设置允许输出
            urlConn.setDoOutput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencode");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == HttpStatus.SC_OK) {
                // 获取返回的数据
                result = JSONUtils.convertStreamToString(urlConn.getInputStream());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
