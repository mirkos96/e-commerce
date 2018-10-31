package com.maks.web.additionalService.Impl;

import com.maks.web.additionalService.IAdditionalMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@PropertySource(value = {"classpath:mail.properties"})
public class AdditionalMailServiceImpl implements IAdditionalMailService {

    private final Environment environment;

    @Autowired
    public AdditionalMailServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void sendRegistrationEmail(String recipientEmail) {
        String login = environment.getProperty("login");
        String password = environment.getProperty("password");
        String hash = String.valueOf(recipientEmail.hashCode());
        Properties props = getProperties();
        Session session = getSession(props, login, password);
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            String html = "Test\n" + environment.getProperty("signUpText")
                    + "\n<a href='http://localhost:8080/confirmation?confirmation=" + hash + "'>Sign up!</a>";
            message.setText(html, "UTF-8", "html");
            message.setFrom(new InternetAddress("hotpizzadevelop@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendResetEmail(String recipientEmail, String tempPass) {
        String login = environment.getProperty("login");
        String password = environment.getProperty("password");
        Properties props = getProperties();
        Session session = getSession(props, login, password);
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            String html = "<a>Hello, this is your temporary password, we strongly recommend you " +
                    "to change it in your personal cabinet. Password: " + tempPass + "</a>";
            message.setText(html, "UTF-8", "html");
            message.setFrom(new InternetAddress(environment.getProperty("from")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replyOnUserMessage(String recipientEmail, String replyBody) {
        String login = environment.getProperty("login");
        String password = environment.getProperty("password");
        Properties props = getProperties();
        Session session = getSession(props, login, password);
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setText(replyBody);
            message.setFrom(new InternetAddress(environment.getProperty("from")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            Transport.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", environment.getProperty("host"));
        props.put("mail.smtp.socketFactory.port", environment.getProperty("port"));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", environment.getProperty("host"));
        props.put("mail.smtp.user", environment.getProperty("login"));
        props.put("mail.smtp.password", environment.getProperty("password"));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", environment.getProperty("port"));
        return props;
    }

    private Session getSession(Properties properties, String login, String password) {
        return Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });
    }
}

