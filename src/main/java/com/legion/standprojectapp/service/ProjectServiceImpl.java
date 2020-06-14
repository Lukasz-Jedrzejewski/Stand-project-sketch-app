package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.entity.User;
import com.legion.standprojectapp.interfaces.ProjectService;
import com.legion.standprojectapp.repository.ProjectRepository;
import com.legion.standprojectapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Project project) {
        this.projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectById(long id){
        return this.projectRepository.findById(id);
    }

    @Override
    public void sendMail(Project project, CurrentEvent currentEvent, Branch branch) throws MessagingException {
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
    public void changeBranchId(long id) {
        projectRepository.setBranchIdToNull(id);
    }

    @Override
    public void changeFloorBoardId(long id) {
        projectRepository.setFloorBoardIdToNull(id);
    }

    @Override
    public void changeTypeOfBuildingId(long id) {
        projectRepository.setTypeOfBuildingIdToNull(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project readSingleProject(long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public List<Project> findUserProjects(String companyMail) {
        return projectRepository.findAllByCompanyMailLike(companyMail);
    }

    @Override
    public List<Project> findSorted() {
        return projectRepository.findAllByOrderByCreatedDesc();
    }


}
