package aop;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import controllerTest.service.TestService;

public class AopTestMain {
	public static void main(String[] args) {
		//Java代码初始化spring，获取bean的方式
		/**
		 * spring通过获取ApplicationContext有两种方式，通过ClassPathXmlApplicationContext或者FileSystemXmlApplicationContext;
		 * ClassPathXmlApplicationContext知道从项目的classpath路径下开始找配置文件
		 * FileSystemXmlApplicationContext会从硬盘的绝对路径下读取配置文件。
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-servlet.xml","applicationContext.xml");

		String[] configLocations = {new File("").getAbsolutePath()+"/config/applicationContext.xml"};
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(configLocations);
		
		TestService ts = (TestService) ac.getBean("testService");
		ts.test();
	
	}
}
