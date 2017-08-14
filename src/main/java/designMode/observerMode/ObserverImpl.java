package designMode.observerMode;

//具体观察者
public class ObserverImpl implements Observer{
	@Override
	public void updateMsg(String msg) {
		System.out.println("更新信息:"+msg);
	}

}
