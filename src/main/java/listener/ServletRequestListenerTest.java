package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletRequestListenerTest implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		//System.out.println("ServletRequest destroy");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		//System.out.println("ServletRequest initialize");
	}

}
