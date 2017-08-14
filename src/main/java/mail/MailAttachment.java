package mail;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

public class MailAttachment {
	private MimeMessage message;
	private Session session;
	private Transport transport;

	private String mailHost = "";
	private String mailPort = "";
	private String mailProtocol = "";
	
	private String sender_username = "";
	private String sender_password = "";

	private Properties properties = new Properties();
	public Logger logger = Logger.getLogger(MailAttachment.class);
	
	public MailAttachment() {
		this.MailInit(false);
	}

	public MailAttachment(boolean debug) {
		this.MailInit(debug);
	}

	public MailAttachment(boolean debug,String mailHost,String mailPort,
			String mailProtocol,String sender_username,String sender_password) {
		this.mailHost = mailHost;
		this.mailPort = mailPort;
		this.mailProtocol = mailProtocol;
		
		this.sender_username = sender_username;
		this.sender_password = sender_password;
		
		this.MailInit2(debug);
	}
	
	
	/**
	 * 
	 * @param debug：是否开启调试模式，false：否，true：是
	 * @param propertyPath:邮件配置文件的路径
	 * 
	 * 	邮件配置文件所需的参数：
	 * 		直接给测试时所配参数以及参数值
	 * 		mail.smtp.host=smtp.qq.com
	 * 		mail.smtp.auth=true
	 * 		mail.sender.username=xxx.qq.com
	 * 		mail.sender.password=xxx
	 */
	public MailAttachment(boolean debug,String propertyPath) {
		this.MailInit(debug, propertyPath);
	}
	
	
	/*
	 * 初始化方法
	 */
	private void MailInit(boolean debug) {
		InputStream in = MailAttachment.class.getResourceAsStream("mail-server.properties");
		try {
			properties.load(in);
			this.mailHost = properties.getProperty("mail.smtp.host");
			this.mailPort = properties.getProperty("mail.smtp.host");
			this.mailProtocol = properties.getProperty("mail.smtp.host");
			
			this.sender_username = properties.getProperty("mail.sender.username");
			this.sender_password = properties.getProperty("mail.sender.password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}
	
	
	/*
	 * 初始化方法
	 */
	private void MailInit2(boolean debug) {
				
		properties.put("mail.smtp.host", this.mailHost);
		properties.put("mail.smtp.port", this.mailPort);
		properties.put("mail.transport.protocol", this.mailProtocol);
		properties.put("mail.sender.username", this.sender_username);
		properties.put("mail.sender.password", this.sender_password);
			
		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}
	

	/*
	 * 初始化方法
	 */
	private void MailInit(boolean debug,String propertyPath) {
		
		if(propertyPath == null || propertyPath.equals("")){
			System.out.println("参数propertyPath的值不正确");
			logger.info("参数propertyPath的值不正确");
			return;
		}
		
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(propertyPath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			logger.info(e1);
		}
		try {
			properties.load(in);
			
			this.mailHost = properties.getProperty("mail.smtp.host");
			this.mailPort = properties.getProperty("mail.smtp.host");
			this.mailProtocol = properties.getProperty("mail.smtp.host");
			
			this.sender_username = properties.getProperty("mail.sender.username");
			this.sender_password = properties.getProperty("mail.sender.password");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e);
		}
		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
		message = new MimeMessage(session);
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param sendHtml
	 *            邮件内容
	 * @param receiveUser
	 *            收件人邮箱
	 * @param secondsToSend
	 * 			    抄送邮箱集合           
	 * @param attachment
	 *            附件集合
	 */
	public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, List<String> secondsToSend, List<String> attachments) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(sender_username);
			message.setFrom(from);

			// 收件人
			InternetAddress to = new InternetAddress(receiveUser);
			message.setRecipient(Message.RecipientType.TO, to);

			// 抄送
			if (secondsToSend != null && secondsToSend.size() > 0) {
				// 为每个邮件接收者创建一个地址
				Address[] ccAdresses = new InternetAddress[secondsToSend.size()];
				for (int i = 0; i < secondsToSend.size(); i++) {
					ccAdresses[i] = new InternetAddress(secondsToSend.get(i));
				}
				// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
				message.setRecipients(Message.RecipientType.CC, ccAdresses);
			}

			// 邮件主题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 添加附件的内容
			if (attachments != null && attachments.size() > 0) {
				for (String filePath : attachments) {
					BodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(filePath);
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					attachmentBodyPart.setFileName(MimeUtility.encodeWord(source.getName()));
					multipart.addBodyPart(attachmentBodyPart);
				}
			}

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();

			transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(mailHost, sender_username, sender_password);
			// 发送
			transport.sendMessage(message, message.getAllRecipients());

			System.out.println("send success!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e);
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
					logger.info(e);
				}
			}
		}
	}
}