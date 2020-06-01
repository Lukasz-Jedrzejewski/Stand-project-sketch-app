package com.legion.standprojectapp.interfaces;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;
import javax.mail.MessagingException;
import java.util.Optional;

public interface ProjectService {
    void save(Project project);
    Optional<Project> findProjectById(long id);
    void sendMail(Project project, CurrentEvent currentEvent, Branch branch) throws MessagingException;
    void changeBranchId(long id);
}
