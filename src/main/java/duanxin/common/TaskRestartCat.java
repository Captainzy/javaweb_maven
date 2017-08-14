package duanxin.common;

import duanxin.modem.service.ModemService;
import duanxin.modem.util.SerialPortsUtil;

/**
 * @author zouyang
 * @time 2017年1月20日 下午12:05:09
 * @description 使用spring quartz调度器，让短信猫在特定的时间复位重启，防止短信猫长时间工作崩溃
 */
public class TaskRestartCat {
	public void restartMessageCat(){
		System.out.println("开始定时重启任务");
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
			}
			
		}).start();
	}
}
