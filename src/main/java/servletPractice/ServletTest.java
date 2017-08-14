package servletPractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;

import javax.jws.WebParam;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.utils.SystemUtils;

@WebServlet(value="/servletTest",initParams={@WebInitParam(name="param1",value="123"),@WebInitParam(name="param2",value="2222")})
public class ServletTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter writer = response.getWriter();
//		writer.write("<p>this is a test.</p>");
//		Enumeration<String> obj = request.getHeaderNames();
//		while(obj.hasMoreElements()){
//			String headerName = obj.nextElement();
//			System.out.println(headerName + " : " + request.getHeader(headerName));
//		}
//		
//		System.out.println("pathInfo : "+request.getPathInfo());
//		System.out.println("authType : "+request.getAuthType());
//		System.out.println("contentType : "+request.getContentType());
//		System.out.println("contextPath : "+request.getContextPath());
//		System.out.println("pathInfo : "+request.getPathInfo());
//		System.out.println("servletPath : "+request.getServletPath());
//		System.out.println("requestUrl : "+request.getRequestURI());
//		
//		System.out.println("----------------------------------------------");
//		ServletConfig config = getServletConfig();
//		Enumeration<String> paramNames = config.getInitParameterNames();
//		while(paramNames.hasMoreElements()){
//			String paramName = paramNames.nextElement();
//			System.out.println(paramName + " : " + config.getInitParameter(paramName));
//		}
//		
//		System.out.println("-------------------------------------------------");
//		ServletContext context = this.getServletContext();
//		context.setAttribute("contextText", "this is a context text");
//		
//		HttpSession session = request.getSession();
	}

}
