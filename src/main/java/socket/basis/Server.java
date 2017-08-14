package socket.basis;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		int port = 8899;
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept();
		InputStreamReader reader = new InputStreamReader(client.getInputStream(),"UTF-8");
		
	
		char[] chars = new char[64];
		StringBuffer sb = new StringBuffer();
		String str = "";
		int len;
		while((len = reader.read(chars))!=-1){
		     str = new String(chars,0,len);
		     if((str.indexOf("eof")!=-1)){
		    	 sb.append(str,0,str.indexOf("eof"));
		    	 break;
		     }
		     sb.append(str);
		}
		System.out.println(sb);
		
		OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
		writer.write("服务端：客户端你好");
		writer.write("eof");
		writer.flush();
		
		reader.close();
		writer.close();
		client.close();
		server.close();
		
	}
}
