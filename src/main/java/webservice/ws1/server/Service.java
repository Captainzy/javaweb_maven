package webservice.ws1.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.ws.Endpoint;

@WebService
public interface Service {
	@WebMethod
	public String getName(String name);
	@WebMethod
	public List<TestBean> getTestBeanList();
}
