package controllerTest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import socket.thread.InitServerSocket;
import socket.thread.InitServerSocket.ThreadHandleSocket;


@Controller
@RequestMapping("/system/testSystem")
public class TestSystemController {

	private static Socket socket1;
	private static Socket socket2;
	@RequestMapping(value="/test",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String test(){
		return "你好";
	}
	
	@RequestMapping(value="/socketTest1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String socketTest(HttpServletRequest request){
		try {
			
			socket1 = new Socket("127.0.0.1", 8899);
			socket2 = new Socket("127.0.0.1",8899);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "建立两个连接";
	}
	@RequestMapping(value="/socketTest2",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String socketTest2(HttpServletRequest request) throws SocketException{
		
			
			InitServerSocket iss = (InitServerSocket) request.getServletContext().getAttribute("serverSocket");
			String str = "";
//			for(Map.Entry entry:iss.getHandleSocketMap().entrySet()){
//				ThreadHandleSocket hs = (ThreadHandleSocket) entry.getValue();
//				if(hs.getClient().isClosed()){
//					str += entry.getKey() + "  关闭\n";
//
//				}else{
//					str += entry.getKey() + "  打开\n";
//				}
//			}
//			str += "当前连接数："+iss.getHandleSocketMap().size();
			
			for(int i = 0;i<iss.getHandleSocketList().size();i++){
				ThreadHandleSocket hs = iss.getHandleSocketList().get(i);
				if(hs.getClient().isClosed()){
					str += "  关闭\n";
				}else{
					str += "  打开\n";
				}
			}
			str += "当前连接数："+iss.getHandleSocketList().size();
		return str;
	}
	
	@RequestMapping(value="/socketTest3",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String socketTest3(HttpServletRequest request){
		try {
			Socket socket = new Socket("127.0.0.1",8899);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "打开一个局部连接";
	}
	
	@RequestMapping(value="/socketTest4",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String socketTest4(HttpServletRequest request){
		try {
			Socket socket = new Socket("127.0.0.1",8899);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "打开一个局部连接";
	}
	
	@RequestMapping(value="/closeSocket1",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String closeSocket1(HttpServletRequest request){
		System.out.println("关闭一个连接");
		try {
			socket1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "关闭一个连接";
	}
	
	@RequestMapping(value="/closeSocket",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String closeSocket(HttpServletRequest request){
		System.out.println("关闭一个连接");
		try {
			socket2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "关闭一个连接";
	}
}
