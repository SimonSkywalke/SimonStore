package utils;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * �����ʼ��Ĺ�����
 * ע�⣺�˴�ʾ�������õ����Ѻ�������˻�itcast_shitou������Ϊst123456���û������ʼ����ͣ�
 * 		�����ں���ѧϰ��ʾʱ�������޸�Ϊ���������˻���Ϣ��ͬʱ����SMTP��������ʼ��������
 */
public class MailUtils {
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����Properties���󣬲������ʼ��������Ļ�����Ϣ
		Properties props = new Properties();
		// 1.1 �����ʼ�����Э��SMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// 1.2 ����SMTP��������ַ���˴����õ����Ѻ������SMTP��������ַ���������������������Ҫ��Ӧ�޸�
		props.setProperty("mail.host", "smtp.sohu.com");
		// 1.3 ����SMTP�������Ƿ���Ҫ�û���֤����Ҫ��֤����Ϊtrue
		props.setProperty("mail.smtp.auth", "true");
		// 1.4 ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				// �����Ѻ������Ӧ�����˵������˻������룬�����û���֤
				return new PasswordAuthentication("itcast_shitou", "st123456");
			}
		};
		Session session = Session.getInstance(props, auth);
		// 2.����һ��Message���ö����൱���ʼ�����
		Message message = new MimeMessage(session);
		// �����Ѻ������������ַ
		message.setFrom(new InternetAddress("itcast_shitou@sohu.com")); 
		// ���÷��ͷ�ʽ�������
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 
		message.setSubject("�û�����");
		// �����ʼ���Ϣ�ͱ����ʽ
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.�����ʼ�
		Transport.send(message);
	}
}
