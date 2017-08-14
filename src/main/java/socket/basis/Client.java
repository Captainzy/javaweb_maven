package socket.basis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws InterruptedException{
		String host = "127.0.0.1";
		int port = 8899;
		Socket client = null;
		OutputStreamWriter writer = null;
		InputStreamReader reader = null;
		try {
			client = new Socket(host, port);
			
			writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
			writer.write("客户端：服务端你好");
			writer.write("eof");
			writer.flush();
			
			char[] chars = new char[64];
			StringBuffer sb = new StringBuffer();
			int len;
			String str = "";
			
			reader = new InputStreamReader(client.getInputStream(), "UTF-8");
			while((len=reader.read(chars))!=-1){
				str = new String(chars,0,len);
				if(str.indexOf("eof")!=-1){
					sb.append(str.substring(0, str.indexOf("eof")));
					break;
				}
				sb.append(str);
			}
			System.out.println("--------------------");
			System.out.println(sb);
			
			writer.close();
			reader.close();
			client.close();
			 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
