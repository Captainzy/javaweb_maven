package netty.appFramework.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserInfo {
	private int userId;
	private String loginName;
	private String loginPwd;
	private String linkMan;
	private String linkPhone;
	private String linkEmail;
	private String leaderName;
	private String leaderPhone;
	private int userStatus;
	private Date lastLoginTime;
	private String lastLoginIp;
	private int ipLockStatus;
	private int[] roleIdArr;
	private List<Map<String,Object>> rightMapList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLinkEmail() {
		return linkEmail;
	}

	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getIpLockStatus() {
		return ipLockStatus;
	}

	public void setIpLockStatus(int ipLockStatus) {
		this.ipLockStatus = ipLockStatus;
	}

	public int[] getRoleIdArr() {
		return roleIdArr;
	}

	public void setRoleIdArr(int[] roleIdArr) {
		this.roleIdArr = roleIdArr;
	}

	public List<Map<String, Object>> getRightMap() {
		return rightMapList;
	}

	public void setRightMap(List<Map<String, Object>> rightMapList) {
		this.rightMapList = rightMapList;
	}

}
