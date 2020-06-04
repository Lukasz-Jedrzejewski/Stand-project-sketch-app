package com.legion.standprojectapp.interfaces;

import com.legion.standprojectapp.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    File save(MultipartFile file);
    List<File> readFiles();
}
