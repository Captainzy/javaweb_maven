package webservice.ws2.server;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface OrderProcess {
	@WebMethod
	public String processOrder(Order order);
	@WebMethod
	public List<Order> getOrderList();
	@WebMethod
	public Map<String,Order> getOrderMap();
}
