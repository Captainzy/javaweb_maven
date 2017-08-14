package designMode.proxyPractice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zouyang
 * @time 2016年12月27日 上午10:14:18
 * @description 
 * 动态代理：
 * 		动态地创建代理并动态地处理对所代理方法的调用。
 */
public class DynamicProxyObject implements InvocationHandler {

	private Object proxy;

	public DynamicProxyObject(Object proxy) {
		this.proxy = proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		 //在转调具体目标对象之前，可以执行一些功能处理
		System.out.println(" RealObject -->" + proxy.getClass() + "\n method -->" + method + " \n args -->" + args);
		if (args != null && args.length > 0) {
			for (Object obj : args) {
				System.out.print(obj + "---");
			}
		}
		//转调具体目标对象的方法
		result = method.invoke(this.proxy, args);
		//在转调具体目标对象之后，可以执行一些功能处理
		//......
		return result;
	}

}
