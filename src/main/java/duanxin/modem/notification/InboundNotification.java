package duanxin.modem.notification;

import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
/**
 * @author zouyang
 * @time 2017年1月17日 下午2:19:49
 * @description 接收短信监听器
 */
public class InboundNotification implements IInboundMessageNotification  {

	@Override
	public void process(AGateway gateway, MessageTypes msgType, InboundMessage inboundMsg) {
		if(MessageTypes.INBOUND.equals(msgType)){
			System.out.println("收到短信："+inboundMsg.getText()+"\n发件人："+inboundMsg.getOriginator());
			try {
				//读取信息后删除缓存消息
				gateway.deleteMessage(inboundMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
