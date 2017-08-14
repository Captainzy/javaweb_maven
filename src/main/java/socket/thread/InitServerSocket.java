package socket.thread;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitServerSocket implements Runnable {
	private ServerSocket server;
	private static final ExecutorService es = Executors.newFixedThreadPool(10);
	//private static Map<String,ThreadHandleSocket> handleSocketMap = new HashMap<String,ThreadHandleSocket>();
	private static List<ThreadHandleSocket> handleSocketList = new LinkedList<ThreadHandleSocket>();
	public InitServerSocket(){
		int port = 8899;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<ThreadHandleSocket> getHandleSocketList() {
		return handleSocketList;
	}

	public static void setHandleSocketList(List<ThreadHandleSocket> handleSocketList) {
		InitServerSocket.handleSocketList = handleSocketList;
	}

	@Override
	public void run() {
			while(true){
				Socket client = null;
				try {
					while(true){
						
						client = server.accept();
						ThreadHandleSocket ths = new ThreadHandleSocket(client);
						es.execute(ths);
						//handleSocketMap.put(String.valueOf((handleSocketMap.size()+1)), ths);
						handleSocketList.add(ths);
						
						
						for(int i = 0;i<handleSocketList.size();i++){
							ThreadHandleSocket hs = handleSocketList.get(i);
							if(hs.getClient().isClosed()){
								handleSocketList.remove(i);
							}
						}
//						String s = "";
//						for(Map.Entry entry:handleSocketMap.entrySet()){
//							ThreadHandleSocket hs = (ThreadHandleSocket) entry.getValue();
//							if(hs.getClient().isClosed()){
//								s = (String) entry.getKey();
//							}
//						}
//						handleSocketMap.remove(s);
//						
//						System.out.println("当前连接数："+handleSocketMap.size());
						System.out.println("当前连接数："+handleSocketList.size());
					}
				} catch (IOException e) {
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
	}
	
	public class ThreadHandleSocket extends Thread {
		private Socket client;
		
		public ThreadHandleSocket(){}
		
		public ThreadHandleSocket(Socket client){
			this.client = client;
		}
		
		public Socket getClient() {
			return client;
		}

		public void setClient(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			
			try {
				
				InputStreamReader reader = new InputStreamReader(client.getInputStream());
				System.out.println("服务端读操作");
				int len = 0;
				char[] cbuf = new char[64];
				StringBuffer sb = new StringBuffer();
				String str = "";
				while((len = reader.read(cbuf))!=-1){
					str = new String(cbuf,0,len);
					if(str.indexOf("eof")!=-1){
						sb.append(str,0,str.indexOf("eof"));
						break;
					}
					sb.append(str);
				}
				
				
				OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
				System.out.println("服务端写操作");
				writer.write(sb.toString());
				writer.flush();
				
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
