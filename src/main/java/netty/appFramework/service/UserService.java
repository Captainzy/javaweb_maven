package netty.appFramework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import netty.appFramework.dao.UserDao;
import netty.appFramework.model.APIResult;
import netty.appFramework.model.UserInfo;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public APIResult<UserInfo> userLogin(String userName,String password){
		UserInfo userInfo = userDao.userLogin(userName, password);
		APIResult<UserInfo> result = new APIResult<UserInfo>();
		if(userInfo != null && userInfo.getUserStatus() == 1){
			Integer[] roleIdArr = userDao.queryUserRoles(userInfo.getUserId());
			List<Map<String,Object>> rightMapList = userDao.queryUserRights(roleIdArr);
			userInfo.setRightMap(rightMapList);
			result.setCode(1);
			result.setData(userInfo);
			result.setMsg("登录成功");
			return result;
		}else{
			if(userInfo == null){
				result.setCode(-1);
				result.setMsg("用户名或密码错误！！！");
			}else{ 
				result.setCode(0);
				result.setMsg("账号暂停使用，请联系管理员！！！");
			}
		}
		return result;
	}
}
