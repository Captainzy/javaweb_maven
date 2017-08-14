package designMode.subscribePublishMode;

import java.util.ArrayList;
import java.util.List;

//调度中心
public class DispatchCenter {
	private List<Subscribe> subscribeList=new ArrayList<Subscribe>();
	
	public void notifySubscribe(String msg){
		for(int i=0;i<subscribeList.size();i++){
			subscribeList.get(i).readMsg(msg);
		}
	}
	
	public void addSubscribe(Subscribe sub){
		subscribeList.add(sub);
	}
	
	public void removeSubscribe(int i){
		subscribeList.remove(i);
	}
	
}
