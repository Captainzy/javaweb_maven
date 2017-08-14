package designMode.proxyPractice;

import java.lang.reflect.Proxy;

/**
 * @author zouyang
 * @time 2016年12月27日 上午9:29:13
 * 代理模式一般涉及到三个角色：
 *	1.抽象角色：
 *		声明真实对象和代理对象的共同接口(ComInterface)
 *	2.代理角色(ProxyObject)：
 *		代理对象内部包含有真实角色的引用(proxy)，从而可以操作真实角色，
 *		同时代理对象 与真实对象有相同的接口，能在任何时候代替真实对象，
 *		同时代理对象可以在执行真实对象前后加入特定的逻辑以实现功能的扩展。
 *	3.真实角色(RealObject)：
 *		代理角色所代表的真实对象，是我们最终要引用的对象
 */
public class ProxyTest {
	public static void main(String[] args) {
		
		System.out.println("通过对象直接调用---------");
		test(new RealObject());
		
		System.out.println("通过代理对象调用---------");
		test(new ProxyObject(new RealObject()));
		
		ComInterface proxy = (ComInterface) Proxy.newProxyInstance(RealObject.class.getClassLoader(),
				RealObject.class.getInterfaces(), new DynamicProxyObject(new RealObject()));
		System.out.println("动态调用代理--------");
		test(proxy);
		
		System.out.println("通过代理工厂类动态调用---------");
		ComInterface dynamicProxy = (ComInterface) DynamicProxyFactory.getRealObjectProxy(RealObject.class.getClassLoader(),
				new RealObject());
		test(dynamicProxy);
	}

	public static void test(ComInterface obj) {
		obj.methodA("TEST");
		obj.methodB();
	}
}
