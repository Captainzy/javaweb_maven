package mail;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author zouyang
 * @time 2017年2月7日 上午9:05:16
 * @description 发送邮件工具类
 */
public class EmailSendUtil {
	/**
	 * @author zouyang
	 * @time 2017年2月7日 上午9:53:38
	 * @description TODO
	 * @param essi
	 * @param messageType
	 *            邮件以什么形式发送，"HTML"以html的形式发送，"TEXT"以文本形式发送
	 */
	public static boolean sendEmail(EmailSendServerInfo essi, String messageType) {
		EmailAuthenticator ea = null;
		if (essi.isValidate()) {
			// 如果需要身份验证，则创建一个身份验证器
			ea = new EmailAuthenticator(essi.getUsername(), essi.getPassword());
		}
		// 根据邮件会话属性和身份验证器构造一个发送邮件的session
		Session sendEmailSession = Session.getInstance(essi.getProperties(), ea);
		// 测试的时候设为true
		sendEmailSession.setDebug(EmailServerConstant.IS_DEBUG);

		try {
			// 根据session创建一个邮件消息
			MimeMessage mm = new MimeMessage(sendEmailSession);

			if (essi.getSenderAddress() == null || "".equals(essi.getSenderAddress())) {
				System.out.println("邮件发送者地址不能为空");
				return false;
			}
			// 创建邮件发送者地址，并存放到邮件消息中
			Address senderAddress = new InternetAddress(essi.getSenderAddress());
			mm.setFrom(senderAddress);

			if (essi.getRecipientAddress() == null || essi.getRecipientAddress().length <= 0) {
				System.out.println("邮件至少需要一个接收者");
				return false;
			}
			// 创建邮件接收者地址数组，并存放到邮件消息中
			Address[] recipientAddresses = new InternetAddress[essi.getRecipientAddress().length];
			for (int i = 0; i < essi.getRecipientAddress().length; i++) {
				recipientAddresses[i] = new InternetAddress(essi.getRecipientAddress()[i]);
			}
			mm.setRecipients(RecipientType.TO, recipientAddresses);

			if (essi.getCopyTOAddress() != null && essi.getCopyTOAddress().length > 0) {
				// 创建邮件抄送对象地址数组，并存放到邮件消息中
				Address[] copyToAddresses = new InternetAddress[essi.getCopyTOAddress().length];
				for (int i = 0; i < essi.getCopyTOAddress().length; i++) {
					copyToAddresses[i] = new InternetAddress(essi.getCopyTOAddress()[i]);
				}
				mm.setRecipients(RecipientType.CC, copyToAddresses);
			}

			if (essi.getEmailTheme() != null && !"".equals(essi.getEmailTheme())) {
				// 设置邮件主题
				mm.setSubject(essi.getEmailTheme(), "UTF-8");
			}

			// 用MimeMultipart存储邮件消息的主要内容和附件
			Multipart mp = new MimeMultipart();
			// 设置邮件消息主要内容
			if ("HTML".equals(messageType.toUpperCase())) {
				// 以html的形式发送邮件
				BodyPart bpContent = new MimeBodyPart();
				bpContent.setContent(essi.getEmailContent() == null ? "" : essi.getEmailContent(),
						"text/html;charset=UTF-8");
				mp.addBodyPart(bpContent);
			} else if ("TEXT".equals(messageType.toUpperCase())) {
				// 以文本形式发送邮件
				BodyPart bpContent = new MimeBodyPart();
				bpContent.setText(essi.getEmailContent() == null ? "" : essi.getEmailContent());
				mp.addBodyPart(bpContent);
			} else {
				// 默认以文本形式发送邮件
				BodyPart bpContent = new MimeBodyPart();
				bpContent.setText(essi.getEmailContent() == null ? "" : essi.getEmailContent());
				mp.addBodyPart(bpContent);
			}

			if (essi.getEmailAttachment() != null && essi.getEmailAttachment().length > 0) {
				// 存在附件，发送带附件的邮件
				for (File f : essi.getEmailAttachment()) {
					BodyPart bpFile = new MimeBodyPart();
					// 得到文件的数据源
					FileDataSource fds = new FileDataSource(f);
					// 得到文件
					DataHandler dh = new DataHandler(fds);
					// 通过BodyPart添加文件和文件名
					bpFile.setDataHandler(dh);
					bpFile.setFileName(MimeUtility.encodeWord(fds.getName()));// 文件名可能有中文
					mp.addBodyPart(bpFile);
				}
			}
			// 将MimeMultipart对象设置为邮件的主要内容
			mm.setContent(mp);
			// 设置邮件发送时间
			mm.setSentDate(new Date());

			/*
			 * 由于Session对象中注册了Authenticator子类对象，因此可以直接
			 * 从该Authenticator子类对象中获取登录的相关信息，故直接使用 Transport 抽象类中静态 send()
			 * 方法来简化代码编写
			 */
			Transport.send(mm);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
