package designMode.subscribePublishMode;

public class Test {
	public static void main(String[] args) {
		
		Publisher pub = new Publisher();
		
		Subscribe sub1 = new Subscribe_01();
		pub.getDispatchCenter().addSubscribe(sub1);
		
		Subscribe sub2 = new Subscribe_02();
		pub.getDispatchCenter().addSubscribe(sub2);
		
		pub.pubMsg("发布测试");
	}
}
