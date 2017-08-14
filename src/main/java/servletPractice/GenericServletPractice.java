package servletPractice;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: GenericServletPractice
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zouyang
 * @date 2016年8月9日 下午11:28:42
 * 
 */
@WebServlet(urlPatterns={"/genericServlet2","/genericServlet3"},
			initParams={@WebInitParam(name="p1",value="p1value"),@WebInitParam(name="p2",value="p2value")})
public class GenericServletPractice extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
//		Object str = this.getServletContext().getAttribute("contextText");
//		System.out.println("GenericServletPractice......");
//		System.out.println(str==null?"":str.toString());
//		this.getServletContext().setAttribute("listener", "listener test");
	}

}
