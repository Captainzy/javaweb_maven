package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import freemarker.template.Configuration;

@WebListener
public class ServletContextListenerTest implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("ServletContext destroy");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
