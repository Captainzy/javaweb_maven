package mail;

import java.io.File;
import java.util.Properties;

/**
 * @author zouyang
 * @time 2017年2月6日 下午5:03:12
 * @description 发件服务器参数信息
 */
public class EmailSendServerInfo {
	private String sendServerHost;// 发件服务器主机地址
	private String sendServerPort;// 发件服务器端口
	private String senderAddress;// 发送者地址
	private String[] recipientAddress;// 接收者地址数组
	private String[] copyTOAddress;// 抄送接收者地址数组
	private String username;// 登陆发送服务器的账户用户名
	private String password;// 登陆发送服务器的账户密码
	private boolean validate;// 是否需要登陆服务器验证
	private String protocol;// 协议
	private String sslFactory;// SSL处理
	private boolean sslFallBack;// 对于非SSL的连接做不做处理
	private String sslPort;// SSL端口
	private String emailTheme;// 邮件主题
	private String emailContent;// 邮件内容
	private File[] emailAttachment;// 邮件附件

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.sendServerHost);
		p.put("mail.smtp.port", this.sendServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		p.setProperty("mail.transport.protocol", this.protocol);// 必须选择协议
		// SSL加密
		p.setProperty("mail.smtp.socketFactory.class", this.sslFactory);
		// 只处理SSL的连接,对于非SSL的连接不做处理
		p.setProperty("mail.smtp.socketFactory.fallback", this.sslFallBack ? "true" : "false");
		// SSL端口
		p.setProperty("mail.smtp.socketFactory.port", this.sslPort);
		return p;
	}

	public String getSendServerHost() {
		return sendServerHost;
	}

	public void setSendServerHost(String sendServerHost) {
		this.sendServerHost = sendServerHost;
	}

	public String getSendServerPort() {
		return sendServerPort;
	}

	public void setSendServerPort(String sendServerPort) {
		this.sendServerPort = sendServerPort;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String[] getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String[] recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String[] getCopyTOAddress() {
		return copyTOAddress;
	}

	public void setCopyTOAddress(String[] copyTOAddress) {
		this.copyTOAddress = copyTOAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSslFactory() {
		return sslFactory;
	}

	public void setSslFactory(String sslFactory) {
		this.sslFactory = sslFactory;
	}

	public boolean isSslFallBack() {
		return sslFallBack;
	}

	public void setSslFallBack(boolean sslFallBack) {
		this.sslFallBack = sslFallBack;
	}

	public String getSslPort() {
		return sslPort;
	}

	public void setSslPort(String sslPort) {
		this.sslPort = sslPort;
	}

	public String getEmailTheme() {
		return emailTheme;
	}

	public void setEmailTheme(String emailTheme) {
		this.emailTheme = emailTheme;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public File[] getEmailAttachment() {
		return emailAttachment;
	}

	public void setEmailAttachment(File[] emailAttachment) {
		this.emailAttachment = emailAttachment;
	}

}
