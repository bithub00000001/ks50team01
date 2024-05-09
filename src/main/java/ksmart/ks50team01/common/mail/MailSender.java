package ksmart.ks50team01.common.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void sendMail(String recipient, String subject, String body) throws MessagingException {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.naver.com");
	    props.put("mail.smtp.port", "465"); // 네이버 메일 서버는 587 포트 사용
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // TLS 버전 변경
	    props.put("mail.smtp.ssl.ciphersuites", "TLS_RSA_WITH_AES_256_CBC_SHA256"); // 암호화 방식 변경

	    // 네이버 계정 정보
	    String username = "khm3943@naver.com";
	    String password = "rhkrgyals@2";

	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(username));
	    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	    message.setSubject(subject);
	    message.setText(body);

	    Transport.send(message);
	}
}