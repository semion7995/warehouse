package email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
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
        MimeMultipart multipart = new MimeMultipart();

//Первый кусочек - текст письма
        MimeBodyPart part1 = new MimeBodyPart();
        part1.addHeader("Content-Type", "text/plain; charset=UTF-8");
        part1.setDataHandler(new DataHandler("Письмо с файлом!!", "text/plain; charset=\"utf-8\""));
        multipart.addBodyPart(part1);
//Второй кусочек - файл
        File file = new File("C:\\enviroment\\BazaKusp.xml.zip");
        MimeBodyPart part2 = new MimeBodyPart();
        part2.setFileName(MimeUtility.encodeWord(file.getName()));
        part2.setDataHandler(new DataHandler(new FileDataSource(file)));
        multipart.addBodyPart(part2);
        message.setContent(multipart);


        Transport tr = mailSession.getTransport();
        tr.connect("devsemion@gmail.com", "ivluiidsyoounanp");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();

    }
}