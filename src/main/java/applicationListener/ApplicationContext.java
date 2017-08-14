package applicationListener;

import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceException;

/**
 * @ClassName: ApplicationContext
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zouyang
 * @date 2016年7月11日 下午9:11:18
 * 
 */

public class ApplicationContext {
	private static final String APP_CONTEXT_NAME = "appContext";
	private static final String BASE_PATH = "basePath";
	private static ApplicationContext appContext = null;
	private ServletContext servletContext;
	private String basePath;
	
	public ApplicationContext(){
		super();
	}
	
	public static ApplicationContext getApplicationContext(){
		if(appContext == null){
			appContext = new ApplicationContext();
		}
		return appContext;
	}
	
	public static ApplicationContext getApplicationContext(ServletContext servletContext){
		ApplicationContext appContext = (ApplicationContext) servletContext.getAttribute(APP_CONTEXT_NAME);
		if(appContext == null){
			throw new WebServiceException("ApplicationContext is not registed!!");
		}
		return appContext;
	}
	
	public void register(ServletContext servletContext){
		this.servletContext = servletContext;
		this.servletContext.setAttribute(APP_CONTEXT_NAME, this);
	}
	
	public void setBasePath(String basePath){
		this.basePath = basePath;
		this.servletContext.setAttribute(BASE_PATH, basePath);
	}
	
	public String getBasePath(){
		return this.basePath;
	}
}
