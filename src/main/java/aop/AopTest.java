package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component  //注意：切面必须是 IOC 中的 bean：添加了 @Component 注解或者将切面在xml文件中配置
public class AopTest {
	/**
	 * 表达式  execution (* com.impl..*.*(..))
	 * 第一个“*”号代表所有返回值为任意类型
	 * “..”代表com.impl包和所有的子包
	 * 第二个“*”号代表任意类，
	 * 第三个“*”号代表任意方法
	 * 
	 */
	@Pointcut("execution(* controllerTest.*.*(..))")
	public void testPointCut(){}
	
	@Before("testPointCut()")
	public void beforePointCut(JoinPoint point){
		System.out.println("====================beforePointCut=-=====================");
	}
	
	@After("testPointCut()")
	public void afterPointCut(){
		System.out.println("====================afterPointCut=-=====================");
	}
		
}
