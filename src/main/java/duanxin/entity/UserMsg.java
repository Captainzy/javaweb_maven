package duanxin.entity;

public class UserMsg {
	public String phone;
	public String msg;

	public UserMsg() {

	}

	public UserMsg(String phone, String msg) {
		this.msg = msg;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
