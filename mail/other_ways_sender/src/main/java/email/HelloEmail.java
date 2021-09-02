package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

public class HelloEmail {
    public static void main(String[] args) throws MessagingException {
        //Объект properties хранит параметры соединения.
        //Для каждого почтового сервера они разные.
        //Если не знаете нужные - обратитесь к администратору почтового сервера.
        //Ну или гуглите;=)
        //Конкретно для Yandex параметры соединения можно подсмотреть тут:
        //https://yandex.ru/support/mail/mail-clients.html (раздел "Исходящая почта")

        Properties properties = new Properties();
        //Хост или IP-адрес почтового сервера
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Требуется ли аутентификация для отправки сообщения
        properties.put("mail.smtp.auth", "true");
        //Порт для установки соединения
        properties.put("mail.smtp.socketFactory.port", "587");
        //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        properties.put("mail.smtp.starttls.enable","true");
//        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //Создаем соединение для отправки почтового сообщения
        Session session = Session.getDefaultInstance(properties,
                //Аутентификатор - объект, который передает логин и пароль
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("devsemion@gmail.com", "ivluiidsyoounanp");
                    }
                });


        //Создаем новое почтовое сообщение
        Message message = new MimeMessage(session);
        //От кого
        message.setFrom(new InternetAddress("semaqwinnert@gmail.com"));
        //Кому
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("semyon-zhukov@bk.ru"));
        //Тема письма
        message.setSubject("Очень важное письмо!!!");
        //Текст письма
        message.setText("Hello, Email!");
        //Поехали!!!
        Transport.send(message);
    }
}
