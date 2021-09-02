package email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ExampleMail {
    public static void main(String[] args) throws IOException, MessagingException {

        final Properties properties = new Properties();

        properties.load(ExampleMail.class.getClassLoader().getResourceAsStream("mail.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress("devsemion@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("semyon-zhukov@bk.ru"));
        message.setSubject("hello");
        message.setText("Hi");

        Transport tr = mailSession.getTransport();
        tr.connect("devsemion@gmail.com", "ivluiidsyoounanp");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();

    }
}