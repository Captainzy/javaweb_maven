package socket.thread;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerThread implements Runnable{
	private Socket client;
	private static int i = 0;
	@Override
	public void run() {
		
		InputStreamReader reader = null;
		OutputStreamWriter writer = null;
		try {
			Thread.sleep(100);
			reader = new InputStreamReader(client.getInputStream(),"UTF-8");
			StringBuffer sb = new StringBuffer();
			char[] chars = new char[64];
			int len;
			String str = "";
			while((len=reader.read(chars))!=-1){
				str = new String(chars,0,len);
				if(str.indexOf("eof")!=-1){
					sb.append(str,0,str.indexOf("eof"));
					break;
				}
				sb.append(str);
			}
			System.out.println("来自客户端的消息："+sb);

			writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
			writer.write("客户端你好，我是服务端"+i+"号");
			writer.write("eof");
			writer.flush();
			
			reader.close();
			writer.close();
			this.client.close();
		} catch (IOException | InterruptedException  e) {
			e.printStackTrace();
		}
		
	}
	
	public ServerThread(Socket client){
		this.client = client;
	}
	
	public static void main(String[] args){
		int port = 8899;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			while(true){
				ServerThread serverThread = new ServerThread(server.accept());
				Thread thread = new Thread(serverThread);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
