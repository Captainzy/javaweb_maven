package netty.appFramework.model;

/**
 * @title APIResult.java
 * @author SongDaYe
 * @time 2016年5月23日 上午10:07:23
 * @description 封装API接口数据
 * @version V1.0
 */
public class APIResult<T> {
	private int code;//状态码
	private String msg;//消息
	private T data;//数据
	private String token;//安全令牌
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
