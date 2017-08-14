package designMode.subscribePublishMode;

//发布者
public class Publisher {
	private DispatchCenter dispatchCenter;
	
	public Publisher(){
		this.dispatchCenter = new DispatchCenter();
	}
	
	public void pubMsg(String msg){
		this.dispatchCenter.notifySubscribe(msg);
	}
	
	public DispatchCenter getDispatchCenter(){
		return this.dispatchCenter;
	}
}
