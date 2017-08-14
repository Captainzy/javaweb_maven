package designMode.observerMode;

import java.util.ArrayList;
import java.util.List;

//具体被观察着
public class SubjectImpl implements Subject{
	//存储观察者
	private List<Observer> list = new ArrayList<Observer>();
	//新增一个观察者
	@Override
	public void addObserver(Observer observer) {
		list.add(observer);
	}
	//移除一个观察者
	@Override
	public void removeObserver(Observer observer) {
		list.remove(observer);
	}
	//通知所有的观察者
	@Override
	public void notifyAll(String msg) {
		for(int i=0;i<list.size();i++){
			list.get(i).updateMsg(msg);
		}
	}

}
