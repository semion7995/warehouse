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
import java.util.Date;
import java.util.Properties;

public class ExampleMail {
    public static void main(String[] args) throws IOException, MessagingException {
        Date date1 = new Date();

        final Properties properties = new Properties();

        properties.load(ExampleMail.class.getClassLoader().getResourceAsStream("mail.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress("devsemion@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("semyon-zhukov@bk.ru"));
        message.setSubject("Two files!!!", "UTF-8");
        MimeMultipart multipart = new MimeMultipart();

//Первый кусочек - текст письма

        MimeBodyPart part1 = new MimeBodyPart();
        part1.addHeader("Content-Type", "text/plain; charset=UTF-8");
        part1.setDataHandler(new DataHandler("Письмо с файлом!!", "text/plain; charset=\"utf-8\""));
//        part1.setDataHandler(
//                new DataHandler(
//                        "<html><body style=\"background-color: #FFFF00;color: #FF0033;\"><h1>Привет!</h1></body></html>",
//                        "text/html; charset=\"utf-8\""
//                )//шлём html разметку
//        );
        multipart.addBodyPart(part1);
//Второй кусочек - файл
        File file1 = new File("C:\\enviroment\\BazaKusp.xml.zip");
        MimeBodyPart part2 = new MimeBodyPart();
        part2.setFileName(MimeUtility.encodeWord(file1.getName()));
        part2.setDataHandler(new DataHandler(new FileDataSource(file1)));
        multipart.addBodyPart(part2);

        File file2 = new File("C:\\enviroment\\BazaKusp.xml.zip");
        MimeBodyPart part3 = new MimeBodyPart();
        part3.setFileName(MimeUtility.encodeWord(file2.getName()));
        part3.setDataHandler(new DataHandler(new FileDataSource(file2)));
        multipart.addBodyPart(part3);


        message.setContent(multipart);


        Transport tr = mailSession.getTransport();
        tr.connect("devsemion@gmail.com", "ivluiidsyoounanp");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();

        Date date2 = new Date();

        System.out.println(date2.getTime()- date1.getTime());
    }
}