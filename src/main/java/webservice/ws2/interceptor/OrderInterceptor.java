package webservice.ws2.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class OrderInterceptor extends AbstractPhaseInterceptor<Message> {

	public OrderInterceptor() {
		super(Phase.PRE_PROTOCOL);
		System.out.println("webservice拦截器初始化");
	}

	@Override
	public void handleMessage(Message msg) throws Fault {
		System.out.println("OrderInterceptor 自定义拦截器执行");
	}

}
