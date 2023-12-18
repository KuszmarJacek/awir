package awir.lab2.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

//    @Value("${spring.mail.username}")
    String MAIL_LOGIN = "kj46573@zut.edu.pl";

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_LOGIN);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    public void sendMessageWithAttachment(String to, String subject, String body, MultipartFile file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(MAIL_LOGIN);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        helper.addAttachment("picture.png", file);

        javaMailSender.send(message);
    }
}
