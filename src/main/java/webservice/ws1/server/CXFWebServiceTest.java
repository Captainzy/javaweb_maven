package webservice.ws1.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class CXFWebServiceTest implements Service{
	@Override
	public String getName(String name){
		return name;
	}
	@Override
	public List<TestBean> getTestBeanList(){
		List<TestBean> list = new ArrayList<TestBean>();
		list.add(new TestBean("TestBean NO.1"));
		list.add(new TestBean("TestBean NO.2"));
		list.add(new TestBean("TestBean NO.3"));
		return list;
	}
	public static void main(String[] args){
		Endpoint.publish("http://localhost:8888/javaweb/CXFWebserviceTest?wsdl",new CXFWebServiceTest());
	}
}
