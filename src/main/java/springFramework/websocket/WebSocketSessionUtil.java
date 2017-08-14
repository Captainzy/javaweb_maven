package springFramework.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionUtil {
	private static final Map<String,WebSocketSession> wssMap = new HashMap<String,WebSocketSession>();
	
	public static void add(String wssName,WebSocketSession session){
		wssMap.put(wssName, session);
	}
	
	public static WebSocketSession get(String wssName){
		return wssMap.get(wssName);
	}
	
	public static void remove(String wssName){
		if(hasConnection(wssName)){
			try {
				wssMap.get(wssName).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		wssMap.remove(wssName);
	}
	public static boolean hasConnection(String wssName){
		if(wssMap.get(wssName)!=null){
			return false;
		}else{
			return true;
		}
	}
	public static void sendMessage(String wssName,String message){
		WebSocketSession wss = wssMap.get(wssName);
		try {
			wss.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			System.out.println("推送信息失败，发生异常");
			e.printStackTrace();
			wssMap.remove(wssName);
		}
	}
}
