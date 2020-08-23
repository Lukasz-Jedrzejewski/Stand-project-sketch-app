package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    File save(MultipartFile file, Project project);
    List<File> readFiles();
    File getFile(int id);
    List<File> readAllByProjectId(long id);
}
