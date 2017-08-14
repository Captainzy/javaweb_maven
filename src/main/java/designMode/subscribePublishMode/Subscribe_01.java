package designMode.subscribePublishMode;

public class Subscribe_01 implements Subscribe {

	@Override
	public void readMsg(String msg) {
		System.out.println("订阅者01收到订阅的消息："+msg);
	}

}
