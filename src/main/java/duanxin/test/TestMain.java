package duanxin.test;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import duanxin.modem.service.ModemService;
import duanxin.modem.util.SerialPortsUtil;
import gnu.io.CommPortIdentifier;

public class TestMain {
	public static void main(String[] args) {
		final List<Map<String,String>> list = SerialPortsUtil.getAvaliableSerialPortsSet();
    	boolean flag = true;
    	//在独立项目中可以设置quartz调度器，让短信猫在固定的时间重启，放置短信猫时间长了崩溃
    	if(list.size()>0){
	    	for(int i = 0;i<5;i++){
	    		//启动短信猫服务，如果启动失败则重新启动，最多重启5次
	    		System.out.println("开始启动短信猫");
	    		flag = ModemService.initModelService(list);
	    		if(flag){
	    			break;
	    		}
	    	}
    	}else{
    		System.out.println("共有串口："+list.size()+"个可用");
    	}
    	
    	String msg = "第一条短信";
    	String number = "18113152327";
    	ModemService.getInstance().sendMessageToOne(msg, number);
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("重启之前，先停止短信猫服务");
				ModemService.getInstance().stopModemService();
				System.out.println("发送AT指令关闭短信猫");
				SerialPortsUtil.stopMessageCat();
				try {
					Thread.sleep(30*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("发送AT指令开启短信猫");
				SerialPortsUtil.resetMessageCat();
				try {
					Thread.sleep(30*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for(int i = 0;i<5;i++){
		    		System.out.println("启动短信猫服务");
		    		boolean flag = ModemService.getInstance().startModemService();
		    		if(flag){
		    			break;
		    		}
		    	}
		    	System.out.println("发送第二条短信");
		    	String msg = "第二条短信";
		    	String number = "18113152327";
		    	ModemService.getInstance().sendMessageToOne(msg, number);
			}
			
		}).start();

    	
	}
}
