package webservice.ws2;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import webservice.ws2.client.GetOrderMapResponse.Return;
import webservice.ws2.client.GetOrderMapResponse.Return.Entry;
import webservice.ws2.client.Order;
import webservice.ws2.client.OrderProcess;
import webservice.ws2.client.OrderProcessImplService;
import webservice.ws2.interceptor.OrderInterceptor;

public class ClientTestMain {

	public static void main(String[] args) {
		//通过java代码编写客户端的方式
		OrderProcessImplService factory = new OrderProcessImplService();
		OrderProcess op = factory.getOrderProcessImplPort();
		
		//发送请求的客户端对象
		Client client = ClientProxy.getClient(op);
		//客户端的自定义出拦截器
		List<Interceptor<? extends Message>> outInterceptors = client.getOutInterceptors();
		outInterceptors.add(new OrderInterceptor());
		outInterceptors.add(new LoggingOutInterceptor());
		
		Order order = new Order();
		order.setCustomerID("123");
		order.setItemID("1");
		order.setPrice(2.2);
		order.setQty(1);
		String result = op.processOrder(order);
		List<Order> list = op.getOrderList();
		Return r = op.getOrderMap();
		List<Entry> listEntry = r.getEntry();
	    System.out.println(result);
	    System.out.println("---------------------------------");
	    for(Order or:list){
	    	System.out.println(or.getCustomerID()+" "+or.getItemID()+" "+or.getPrice() + " " + or.getQty());
	    }
	    System.out.println("---------------------------------");
	    for(Entry entry:listEntry){
	    	Order or = entry.getValue();
	    	System.out.println(or.getCustomerID()+" "+or.getItemID()+" "+or.getPrice() + " " + or.getQty());
	    }
	    
	    //通过spring获取bean
	    /*ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"webservice-client-beans.xml"});
	    OrderProcess op2 = (OrderProcess) ac.getBean("orderProcess");
		Order order2 = new Order();
		order2.setCustomerID("123");
		order2.setItemID("1");
		order2.setPrice(2.2);
		order2.setQty(1);
		String result2 = op2.processOrder(order2);
		List<Order> list2 = op2.getOrderList();
		Return r2 = op2.getOrderMap();
		List<Entry> listEntry2 = r2.getEntry();
	    System.out.println(result2);
	    System.out.println("---------------------------------");
	    for(Order or:list2){
	    	System.out.println(or.getCustomerID()+" "+or.getItemID()+" "+or.getPrice() + " " + or.getQty());
	    }
	    System.out.println("---------------------------------");
	    for(Entry entry:listEntry2){
	    	Order or = entry.getValue();
	    	System.out.println(or.getCustomerID()+" "+or.getItemID()+" "+or.getPrice() + " " + or.getQty());
	    }*/
	}

}
