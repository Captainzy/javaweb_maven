package duanxin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import duanxin.entity.UserMsg;
import duanxin.modem.constant.SerialPortConstants;
import duanxin.modem.service.ModemService;

@Service
public class MessageService {

	@Autowired
	private JdbcTemplate jt;

	/**
	 * @author zouyang
	 * @time 2017年1月17日 上午10:41:49
	 * @description TODO
	 * @param msg短信内容
	 * @param number电话号码
	 * @return
	 */
	public String sendMsgToSingle(String msg, String number) {
		if (SerialPortConstants.DEBUG) {
			System.out.println("正在发送短信");
			return "发送成功";
		} else {
			if (msg == null || "".equals(msg) || number == null || "".equals(number)) {
				return "信息或者号码不能为空";
			} else {
				ModemService.getInstance().sendMessageToOne(msg, number);
				return "发送成功";
			}
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年1月18日 下午3:14:02
	 * @description 群发消息
	 * @param msg
	 * @param number
	 * @return
	 */
	public String sendMsgToGroup(List<UserMsg> list) {
		if (SerialPortConstants.DEBUG) {
			System.out.println("正在发送短信");
			return "发送成功";
		} else {
			for (UserMsg um : list) {
				if (um.getMsg() == null || "".equals(um.getMsg()) || um.getPhone() == null
						|| "".equals(um.getPhone())) {
					continue;
				} else {
					ModemService.getInstance().sendMessageToOne(um.getMsg(), um.getPhone());
				}
			}
			return "发送成功";
		}
	}
}
