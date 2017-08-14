package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerTest implements HttpSessionListener {
	private static int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HttpSession create");
		count++;
		System.out.println("当前在线人数："+count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HttpSession destroy");
		count--;
		System.out.println("当前在线人数："+count);
	}

}
