package applicationListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import socket.thread.InitServerSocket;

/**
 * @ClassName: ApplicationListenser
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zouyang
 * @date 2016年7月11日 下午9:03:41
 * 
 */

@WebListener//使用这个注解后，不用再web.xml文件里面使用Listener标签配置
public class ApplicationListenser implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("application listener ..........");
		ServletContext servletContext = sce.getServletContext();
		ApplicationContext appContext = ApplicationContext.getApplicationContext();
		appContext.register(servletContext);
		appContext.setBasePath(servletContext.getContextPath());
		
		InitServerSocket serverSocket = new InitServerSocket();
		Thread thread = new Thread(serverSocket);
		thread.start();
		sce.getServletContext().setAttribute("serverSocket", serverSocket);
	}
	
}
