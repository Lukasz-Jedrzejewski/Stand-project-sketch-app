package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.repository.UserRepository;
import com.legion.standprojectapp.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

    public MailServiceImpl(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void sendMailWithProjectDetails(Project project, CurrentEvent currentEvent, Branch branch) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        List<User> admins = userRepository.findByAdmin();
        String mailAddress = "";
        for (User user : admins) {
            mailAddress = user.getCompanyMail();
        }
        helper.setTo(mailAddress);
        helper.setSubject("New sketch");
        helper.setText("<h1>You have new project data!</h1>"+project.toHtml()+ " "
                + currentEvent.toHtml() + " " + branch.toHtml(),true);
        javaMailSender.send(msg);
    }

    @Override
    public void sendVerificationToken(String recipient, String token) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(recipient);
        helper.setSubject("Potwierdzenie rejestracji");
        helper.setText("W celu dokończenia rejestracji kliknij w poniższy link: "
                +"http://localhost:8080/confirm-register?token="+token);
        javaMailSender.send(msg);
    }

    @Override
    public void sendPasswordResetToken(String recipient, String token) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(recipient);
        helper.setSubject("Resetowanie hasła");
        helper.setText("Abt zresetować hasło, kliknij w poniższy link: "
                +"http://localhost:8080/reset-confirmation?token="+token);
        javaMailSender.send(msg);
    }

    @Override
    public void sendContactMessage(String recipient, String topic, String content) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(recipient);
        helper.setSubject(topic);
        helper.setText(content, true);
        javaMailSender.send(msg);
    }
}
