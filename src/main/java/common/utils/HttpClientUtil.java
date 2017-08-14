package common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	/**
	 * @author zouyang
	 * @time 2017年2月6日 上午11:53:47
	 * @description post提交，参数是String类型的键值对（map）
	 * @param url
	 * @param map
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> map) {
		//可以通过httpsClient发送https请求
		//CloseableHttpClient httpsClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory()).build();
		//通过httpClient发送http请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//设置超时时间
		RequestConfig reqConfig = RequestConfig.custom().setConnectTimeout(200).build();
		httpPost.setConfig(reqConfig);
		//加上浏览器客户端标识，模拟下浏览器 设置下User-Agent头消息，避免有些系统做了判断拦截掉httpclient的请求
		httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> m : map.entrySet()) {
			NameValuePair nameValuePair = new BasicNameValuePair(m.getKey(), m.getValue());
			list.add(nameValuePair);
		}

		HttpEntity postEntity = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			postEntity = new UrlEncodedFormEntity(list,"UTF-8");
			httpPost.setEntity(postEntity);
			response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity, "UTF-8");
			//BufferedInputStream bin = new BufferedInputStream(responseEntity.getContent());
			//int contentLent = (int) responseEntity.getContentLength();
			// byte[] b = new byte[2048];
			// bin.read(b);
			// result = EncodingUtils.getString(b, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// get请求
	public static String httpGet(String url) {
		//可以通过httpsClient发送https请求
		//CloseableHttpClient httpsClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory()).build();
		//通过httpClient发送http请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		//设置超时时间
		RequestConfig reqConfig = RequestConfig.custom().setConnectTimeout(200).build();
		httpGet.setConfig(reqConfig);
		//加上浏览器客户端标识，模拟下浏览器 设置下User-Agent头消息，避免有些系统做了判断拦截掉httpclient的请求
		httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		String result = null;
		try {
			response = httpClient.execute(httpGet);
			responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity, "UTF-8");

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author zouyang
	 * @time 2017年2月6日 上午11:52:15
	 * @description post提交，包含不同类型的参数，参数是多个文件（listFile）和一些键值对(map)
	 * @param url
	 * @param listFile
	 * @param map
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String postObject(String url, List<File> listFile, Map<String, String> map) throws FileNotFoundException {
		//可以通过httpsClient发送https请求
		//CloseableHttpClient httpsClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory()).build();
		//通过httpClient发送http请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//设置超时时间
		RequestConfig reqConfig = RequestConfig.custom().setConnectTimeout(200).build();
		httpPost.setConfig(reqConfig);
		//加上浏览器客户端标识，模拟下浏览器 设置下User-Agent头消息，避免有些系统做了判断拦截掉httpclient的请求
		httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
		//带有文件的请求需要设置为浏览器模式
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		for (File f : listFile) {
			String name = f.getName().substring(f.getName().indexOf("."), f.getName().length());
			entityBuilder.addBinaryBody(name, f);

		}
		for (Map.Entry<String, String> e : map.entrySet()) {
			StringBody stringBody = new StringBody(e.getValue(), ContentType.create("text/plain", "UTF-8"));
			entityBuilder.addPart(e.getKey(), stringBody);
		}
		HttpEntity postEntity = entityBuilder.build();
		httpPost.setEntity(postEntity);
		
		CloseableHttpResponse response = null;
		HttpEntity responseEntity = null;
		String result = null;
		try {
			response = httpClient.execute(httpPost);
			responseEntity = response.getEntity();
			result = EntityUtils.toString(responseEntity,"UTF-8");
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @author zouyang
	 * @time 2017年5月16日 下午4:20:55
	 * @description 创建SSL安全连接
	 * @return
	 */
	public static SSLConnectionSocketFactory createSSLConnectionSocketFactory(){
		SSLConnectionSocketFactory scsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				//信任所有
				@Override
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return false;
				}
			}).build();
			//SSLConnectionSocketFactory提供了多个构造函数，如果需要自定义验证机制，则采用其他的够着函数，
			//比如SSLConnectionSocketFactory(SSLContext sslContext, HostnameVerifier hostnameVerifier)
			//其中HostnameVerifier是一个接口，可以实现该接口实现自定义的验证机制
			scsf = new SSLConnectionSocketFactory(sslContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scsf;
	}

}
