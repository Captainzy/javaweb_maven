package designMode.subscribePublishMode;

public class Subscribe_02 implements Subscribe{

	@Override
	public void readMsg(String msg) {
		System.out.println("订阅者02收到订阅的消息："+msg);
	}

}
