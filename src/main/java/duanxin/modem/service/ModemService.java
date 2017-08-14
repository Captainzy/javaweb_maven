package duanxin.modem.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.smslib.AGateway;
import org.smslib.AGateway.Protocols;
import org.smslib.GatewayException;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

import duanxin.modem.constant.SerialPortConstants;
import duanxin.modem.notification.OutboundNotification;

public class ModemService {
	private static Service srv;
	private static ModemService msu;
	static {
		srv = Service.getInstance();
		if (msu == null) {
			getInstance();
		}
	}

	private ModemService() {
	}

	public static ModemService getInstance() {
		if (msu == null) {
			msu = new ModemService();
		}
		return msu;
	}

	/**
	 * @author zouyang
	 * @time 2017年1月17日 下午1:55:11
	 * @description 初始化短信猫服务
	 * @param list
	 *            可用串口列表
	 *  猫服务启动成功则返回true,否则返回false;
	 */
	public static boolean initModelService(List<Map<String, String>> list) {
		// 将猫服务中默认的网关全部移除
//		Collection<AGateway> clt = srv.getGateways();
//		AGateway[] gatewayList = new AGateway[clt.size()];
//		for(int i = 0;i<gatewayList.length;i++) {
//			try {
//				srv.removeGateway(gatewayList[i]);
//			} catch (GatewayException e) {
//				e.printStackTrace();
//			}
//		}
		
		if(SerialPortConstants.INBOUNDMESSAGE){
			try {
				InboundMessage[] msgs = srv.readMessages(MessageClasses.ALL);
				for (InboundMessage msg : msgs) {
					srv.deleteMessage(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 添加接收短信监听器
		//srv.setInboundMessageNotification(new InboundNotification());
		// 添加发送短信监听器
		srv.setOutboundMessageNotification(new OutboundNotification());
		// 根据串口信息列表，生成网关，添加到猫服务中
		for (Map<String, String> m : list) {
			AGateway gateway = new SerialModemGateway("modelPort." + m.get("serialPortName"),
					m.get("serialPortName"), Integer.valueOf(m.get("baudRate")), m.get("manufacturer"), null);
			gateway.setInbound(SerialPortConstants.INBOUNDMESSAGE);// 是否接收短信
			gateway.setOutbound(SerialPortConstants.OUTBOUNDMESSAGE);// 是否发送短信
			gateway.setProtocol(Protocols.PDU);
			try {
				// 添加网关到猫服务中
				srv.addGateway(gateway);
			} catch (GatewayException e) {
				e.printStackTrace();
			}
		}
		// 开启短信猫服务
		try {
			srv.startService();
			System.out.println("短信猫开启成功。。。。。。");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("开启短信猫服务失败.........");
			return false;
		}
	}

	public void stopModemService() {
		try {
			if (srv != null) {
				srv.stopService();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("关闭短信猫服务失败。。。。");
		}
	}
	
	public boolean startModemService(){
		try {
			srv.startService();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void sendMessageToOne(String msg, String number) {
		if (msg == null || "".equals(msg) || number == null || "".equals(number)) {
			System.out.println("发送的信息或者号码不能为空");
		} else {
			OutboundMessage outboundMsg = getOutboundMessage(msg, number);
			try {
				srv.sendMessage(outboundMsg);
			} catch (Exception e) {
				System.out.println("收件人：" + number + "的短信发送失败。。。。");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 下午12:17:47
	 * @description TODO
	 * @param 消息群发
	 */
	public void sendMessageToGroup(String msg, String[] numberGroup) {
		if (msg == null || "".equals(msg)) {
			System.out.println("发送的信息不能为空");
		} else {
			for (String number : numberGroup) {
				if (number == null || "".equals(number)) {
					continue;
				}else{
					OutboundMessage outboundMsg = getOutboundMessage(msg, number);
					try {
						srv.sendMessage(outboundMsg);
					} catch (Exception e) {
						System.out.println("收件人：" + number + "的短信发送失败。。。。");
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 上午10:03:33
	 * @description 读取未读信息
	 */
	public void readMessageOfUnread() {
		try {
			InboundMessage[] msgs = srv.readMessages(MessageClasses.UNREAD);
			for (InboundMessage msg : msgs) {
				// 读取未读信息
				System.out.println("未读信息：" + msg.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 上午10:02:11
	 * @description 删除所有信息
	 */
	public void deleteAllMessage() {
		try {
			InboundMessage[] msgs = srv.readMessages(MessageClasses.ALL);
			for (InboundMessage msg : msgs) {
				srv.deleteMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 上午10:02:30
	 * @description 删除已读信息
	 */
	public void deleteReadedMessage() {
		try {
			InboundMessage[] msgs = srv.readMessages(MessageClasses.READ);
			for (InboundMessage msg : msgs) {
				srv.deleteMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 上午10:02:43
	 * @description 删除未读信息
	 */
	public void deleteUnReadMessage() {
		try {
			InboundMessage[] msgs = srv.readMessages(MessageClasses.UNREAD);
			for (InboundMessage msg : msgs) {
				srv.deleteMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OutboundMessage getOutboundMessage(String msg, String recipient) {
		OutboundMessage outboundMsg = new OutboundMessage();
		outboundMsg.setEncoding(MessageEncodings.ENCUCS2);
		outboundMsg.setDeliveryDelay(2000);
		try {
			outboundMsg.addText(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		outboundMsg.setRecipient(recipient);
		return outboundMsg;
	}

}
