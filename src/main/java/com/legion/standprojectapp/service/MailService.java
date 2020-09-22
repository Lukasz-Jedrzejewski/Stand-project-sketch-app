package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Branch;
import com.legion.standprojectapp.entity.CurrentEvent;
import com.legion.standprojectapp.entity.Project;

import javax.mail.MessagingException;

public interface MailService {
    void sendMailWithProjectDetails(Project project, CurrentEvent currentEvent, Branch branch) throws MessagingException;
    void sendVerificationToken(String recipient, String token) throws MessagingException;
}
