package mail;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmailSendServerInfo essi = new EmailSendServerInfo();
		essi.setValidate(true);
		essi.setUsername(EmailServerConstant.SENDER_USERNAME);
		essi.setPassword(EmailServerConstant.SENDER_PASSWORD);
		essi.setSendServerPort(EmailServerConstant.MAIL_PORT);
		essi.setSendServerHost(EmailServerConstant.MAIL_HOST);
		essi.setSenderAddress(EmailServerConstant.SENDER_USERNAME);
		essi.setProtocol(EmailServerConstant.MAIL_PROTOCOL);
		essi.setSslFactory(EmailServerConstant.SSL_FACTORY);
		essi.setSslFallBack(EmailServerConstant.SSL_FALLBACK);
		essi.setSslPort(EmailServerConstant.SSL_PORT);
		essi.setEmailTheme("问候邮件");
		String[] recipentAddresses = new String[]{"490353104@qq.com"};
		essi.setRecipientAddress(recipentAddresses);
		essi.setEmailContent("测试邮件，这段时间你好吗？");
//		File f = new File("C:\\Users\\TZ\\Desktop\\test.java");
//		essi.setEmailAttachment(new File[]{f});
		
		EmailSendUtil.sendEmail(essi, "text");

	}

}
