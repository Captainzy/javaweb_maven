package designMode.observerMode;

//抽象的被观察者
public interface Subject {
	// 增加观察者
    public void addObserver(Observer observer);
    //删除观察者
    public void removeObserver(Observer observer);
    //通知所有的观察者更新消息
    public void notifyAll(String msg);
}
