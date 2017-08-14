package webservice;

import javax.jws.WebService;

@WebService(endpointInterface="webservice.HelloWorld")
public class CXFWebServiceHelloWorld implements HelloWorld{

	@Override
	public String sayHello() {
		return "hello webservice";
	}

}
