package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding
public class WebServiceServer {
	@WebMethod()
	public void getWebService(){
		System.out.println( "hello webservice");
	}
	public static void main(String[] args){
		/*
		 * 注意：开发web工程的时候，这种方法不太友好。
		 * 我们可以编写一个servlet类，在servlet类的初始化方法中发布webservice，
		 * 这样我们的中间件服务器启动的时候就会帮我们自动webservice了。
		 * */
		Endpoint.publish("http://localhost:8080/javaweb/WebServiceServer", new WebServiceServer());
	}
}
