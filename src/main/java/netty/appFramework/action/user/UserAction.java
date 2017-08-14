package netty.appFramework.action.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import netty.appFramework.common.AppContext;
import netty.appFramework.common.AppContextSingle;
import netty.appFramework.model.APIResult;
import netty.appFramework.service.UserService;

/**
 * @author zouyang
 * @time 2017年3月13日 上午11:44:28
 * @description TODO
 */
@Component
public class UserAction {
	private static AppContext appContext = AppContextSingle.APPCONTEXT.getInstantce();

	@Autowired
	private UserService userService;

	/**
	 * @author zouyang
	 * @time 2017年3月13日 上午11:44:22
	 * @description TODO
	 * @param reqMap
	 * @return
	 */
	public APIResult<?> userLogin(ChannelHandlerContext ctx,Map<String, String> reqMap) {
		APIResult<?> result = new APIResult<>();
		Map<Object, Object> sessionMap = appContext.getSessionMap();
		String userName = reqMap.get("userName");
		String password = reqMap.get("password");
		if (sessionMap.get(ctx) != null) {
			result.setCode(-1);
			result.setMsg("用户已登录，不能重复登录");
			return result;
		} else {
			if (userName != null && !"".equals(userName) && password != null && !"".equals(userName)) {
				result = userService.userLogin(userName, password);
				if (result.getCode() == 1) {
					sessionMap.put(ctx, result.getData());
				}
			}else{
				result.setCode(-1);
				result.setMsg("用户名密码不能为空！！！");
			}
			return result;
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年3月13日 上午11:45:59
	 * @description TODO
	 * @param reqMap
	 * @return
	 */
	public APIResult<?> userLogOut(ChannelHandlerContext ctx) {
		Map<Object, Object> sessionMap = appContext.getSessionMap();
		sessionMap.remove(ctx);
		APIResult<?> result = new APIResult<>();
		result.setCode(1);
		result.setMsg("成功退出登录！！！");
		return result;
	}
}
