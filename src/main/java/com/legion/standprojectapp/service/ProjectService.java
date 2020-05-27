package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void save(Project project) {
        this.projectRepository.save(project);
    }

    public Optional<Project> findProjectById(long id){
        return this.projectRepository.findById(id);
    }

    public void sendMail(Project project, CurrentEvent currentEvent) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("generaljedrzejewski@gmail.com");
        helper.setSubject("New sketch");
        helper.setText("<h1>You have new project data!</h1>"+project.toHtml()+ " " + currentEvent.toHtml(), true);
        javaMailSender.send(msg);
    }

}
