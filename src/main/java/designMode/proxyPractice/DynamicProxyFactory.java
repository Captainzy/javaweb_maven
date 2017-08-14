package designMode.proxyPractice;

import java.lang.reflect.Proxy;

public class DynamicProxyFactory {
	public static Object getRealObjectProxy(ClassLoader classLoader, Object target) {
		DynamicProxyObject dpo = new DynamicProxyObject(new RealObject());
		Object obj = Proxy.newProxyInstance(classLoader, target.getClass().getInterfaces(), dpo);
		return obj;
	}
}
