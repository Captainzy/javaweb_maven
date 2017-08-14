package designMode.proxyPractice;

public class ProxyObject implements ComInterface{

	private ComInterface  proxy;
	
	public ProxyObject(){}
	
	public ProxyObject(ComInterface proxy){
		this.proxy = proxy;
	}
	
	@Override
	public void methodA(String str) {
		System.out.println(str+"in the ProxyObject method A");
		proxy.methodA(str);
	}

	@Override
	public void methodB() {
		System.out.println("in the ProxyObject method B");
		proxy.methodB();
	}

}
