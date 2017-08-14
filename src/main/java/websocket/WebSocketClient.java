package websocket;

import java.io.IOException;
import java.net.URI;

import javax.websocket.*;
import javax.websocket.server.PathParam;

@ClientEndpoint
public class WebSocketClient {
	
	public WebSocketClient(){}
	
	@OnMessage
	public void onMessage(String message){
		System.out.println(message);
	}
	
	@OnClose
	public void onClose(Session session){
//		try {
//			session.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@OnError
	public void onError(Throwable error){
		error.printStackTrace();
	}
	
	public void sendMessage(Session session,String message){
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebSocketContainer container = null;
		Session session = null;
		try {
			container = ContainerProvider.getWebSocketContainer(); 
			session = container.connectToServer(WebSocketClient.class, URI.create("ws://localhost:8181/javaweb/websocketServer"));
			WebSocketClient client = new WebSocketClient();
			client.sendMessage(session, "服务端你好，第一次访问");
           // client.onClose(session);
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
