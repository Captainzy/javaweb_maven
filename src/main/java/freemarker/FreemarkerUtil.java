package freemarker;

import java.io.Writer;
import java.util.Locale;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {
	private static Configuration cfg;
	
	public static void initConfiguration(ServletContext servletContext){
		if(cfg == null){
			 cfg = new Configuration(Configuration.VERSION_2_3_23);
		}
		cfg.setServletContextForTemplateLoading(servletContext, "/WEB-INF/view/freemarker/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
	}
	
	public static void addModelData(Object dataModel,String templateName,Writer out){
		try {
			Template template = cfg.getTemplate(templateName);
			template.setOutputEncoding("UTF-8");
			template.process(dataModel, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
