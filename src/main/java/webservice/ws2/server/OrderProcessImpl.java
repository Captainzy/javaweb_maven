package webservice.ws2.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface="webservice.ws2.server.OrderProcess")
public class OrderProcessImpl implements OrderProcess {

	@Override
	public String processOrder(Order order) {
		String orderId = validate(order);
		return orderId;
	}
	
	private String validate(Order order){
		String custId = order.getCustomerID();
		String itemId = order.getItemID();
		double price = order.getPrice();
		int qty = order.getQty();
		if(custId!=null && itemId!=null && !"".equals(custId) && !"".equals(itemId) && price>0.0 && qty>0){
			return "od123456";
		}
		return null;
	}

	@Override
	public List<Order> getOrderList() {
		List<Order> list = new ArrayList<Order>();
		list.add(new Order("123", "1", 1, 5.5));
		list.add(new Order("456", "2", 2, 2.5));
		list.add(new Order("789", "3", 3, 3.5));
		return list;
	}

	@Override
	public Map<String, Order> getOrderMap() {
		Map<String,Order> map = new HashMap<String,Order>();
		map.put("1", new Order("123", "1", 1, 5.5));
		map.put("2", new Order("456","2",2,2.5));
		return map;
	}
}
