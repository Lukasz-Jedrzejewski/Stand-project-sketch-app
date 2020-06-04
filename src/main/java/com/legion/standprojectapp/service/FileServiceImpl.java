package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.interfaces.FileService;
import com.legion.standprojectapp.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            File newFile = new File(fileName, file.getContentType(), file.getBytes());
            return fileRepository.save(newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<File> readFiles() {
        return fileRepository.findAll();
    }
}