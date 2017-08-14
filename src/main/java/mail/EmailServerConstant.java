package mail;

public abstract class EmailServerConstant {
	public static final boolean IS_DEBUG = true; 
	public static final String MAIL_HOST="smtp.163.com"; //邮件服务器的地址
	public static final String MAIL_PORT="25"; //发邮件端口
	public static final String MAIL_PROTOCOL="smtp"; //发邮件的协议
	public static final String SENDER_USERNAME="18280383754@163.com"; //发邮件的发送人的用户名
	public static final String SENDER_PASSWORD="woshizouyang1994";//发邮件的发送人的密码
	public static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; //使用JSSE的SSL socketfactory来取代默认的socketfactory
	public static final boolean SSL_FALLBACK = false;
	public static final String SSL_PORT = "465";//SSL端口
}
