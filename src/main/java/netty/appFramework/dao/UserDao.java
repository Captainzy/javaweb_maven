package netty.appFramework.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import netty.appFramework.model.UserInfo;


@Component
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserInfo userLogin(String userName, String password) {
		String sql = "SELECT t.user_id AS userId,t.login_name AS loginName,t.login_pwd AS loginPwd,t.link_man AS linkMan"
				+ " t.link_phone AS t.linkPhone,t.linkEmail AS linkEmail,t.leader_name AS t.leaderName,t.leaderPhone AS leaderPhone"
				+ " t.last_login_time AS lastLoginTime,t.lastLoginIp AS lastLoginIp,t.ip_lock_status AS ipLockStatus FROM tb_user t WHERE t.login_name = ? AND t.login_pwd = ?";
		Object[] objs = new Object[] { userName, password };
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql, objs);
		if(result!=null && result.size()>0){
			Map<String,Object> map = result.get(0);
			UserInfo userInfo = new UserInfo();
			try {
				//避免Date为NULL时报错
				ConvertUtils.register(new DateConverter(null), Date.class); 
				BeanUtils.populate(userInfo, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userInfo;
		}
		return null;
	}

	public Integer[] queryUserRoles(int userId){
		String sql = "SELECT t1.role_id AS roleId FROM tb_user t1,tb_user_role_relation t2 WHERE t2.user_id = ? AND t1.role_id = t2.role_id AND t1.role_status = 1 AND t2.relation_status = 1";
		Object[] objs = new Object[]{userId};
		List<Integer> list = jdbcTemplate.queryForList(sql, objs, Integer.class);
		return (Integer[]) list.toArray();
	}
	
	public List<Map<String,Object>> queryUserRights(Integer[] roleIdArr){
		if(roleIdArr.length >0){
			String sql = "SELECT t1.function_id AS functionId,t1.p_function_id AS pFunctionId,t1.function_level AS functionLevel FROM tb_sys_function t1,tb_role_function_relation t2"
					   + " WHERE t2.role_id in(";
			for(int i = 0;i<roleIdArr.length;i++){
				sql += roleIdArr[i]+",";
			}
			sql += "-1) AND t2.function_id = t1.function_id AND t1.function_status = 1 AND t2.relation_status = 1";
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			return list;
		}
		return null;
	}
}
