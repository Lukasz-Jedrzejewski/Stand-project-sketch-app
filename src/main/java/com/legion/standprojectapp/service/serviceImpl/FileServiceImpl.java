package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.service.FileService;
import com.legion.standprojectapp.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;

    }

    @Override
    public File save(MultipartFile file, Project project) {
        String fileName = file.getOriginalFilename();
        try {
            File newFile = new File(fileName, file.getContentType(), file.getBytes(), project);
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

    @Override
    public File getFile(int id) {
        return fileRepository.getOne(id);
    }

    @Override
    public List<File> readAllByProjectId(long id) {
        return fileRepository.findAllByProjectId(id);
    }
}
