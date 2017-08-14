package designMode.proxyPractice;

public class RealObject implements ComInterface{

	@Override
	public void methodA(String str) {
		System.out.println(str+" RealObject method A");
	}

	@Override
	public void methodB() {
		System.out.println("RealObject method B");
	}

}
