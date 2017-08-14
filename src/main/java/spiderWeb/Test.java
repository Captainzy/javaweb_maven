package spiderWeb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class Test {
	public static void main(String[] args){
		String url = "http://www.csdn.net/";
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).build();
		httpGet.setConfig(config);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				InputStream in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				File f = new File("C:\\Users\\zouyang\\Desktop\\yibai.html");
				if(!f.exists()){
					f.createNewFile();
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				String line = null;
				while((line=reader.readLine())!=null){
					System.out.println(line);
					writer.write(line);
				}
				writer.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
