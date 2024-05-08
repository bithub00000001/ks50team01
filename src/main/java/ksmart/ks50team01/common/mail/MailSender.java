package ksmart.ks50team01.common.mail;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    final String ENCODING = "UTF-8";
    final String PORT = "465";
    final String SMTPHOST = "smtp.naver.com";

    Context context;
    String from;
    String to;

    String account;
    String password;

    Properties properties;
    Authenticator auth;
    Session session;
    Message message;
    Multipart multipart;
    MimeBodyPart bodyPart;
    MailcapCommandMap commandMap;

    public MailSender(Context context, String from, String to) {
        this.context = context;
        this.from = from;
        this.to = to;

        properties = new Properties();

        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", SMTPHOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.ssl.trust", SMTPHOST);
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        properties.put("mail.smtp.quit-wait", "false");
        properties.put("mail.smtp.socketFactory.port", PORT);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
    }

    public void auth(String account, String password) throws Exception {
        this.account = account;

        byte[] decoded = Base64.getDecoder()
                .decode(password.getBytes());

        String decodedPassword = new String(decoded);
        String authPassword = decodedPassword.substring(0, decodedPassword.length() - 1);

        this.auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, authPassword);
            }
        };
    }

    public void send(String account, String password) {
        assert account == null || password == null || auth == null;

        try {
            auth(account, password);

            session = Session.getInstance(properties, auth);

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, to));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(context.getSubject());
            message.setSentDate(new Date());

            multipart = new MimeMultipart();
            bodyPart = new MimeBodyPart();

            bodyPart.setText(context.getHtml(), ENCODING, "html");

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            commandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            commandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            commandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            commandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            commandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            commandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(commandMap);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}