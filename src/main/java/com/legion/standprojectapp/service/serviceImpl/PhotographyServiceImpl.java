package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import com.legion.standprojectapp.repository.PhotographyRepository;
import com.legion.standprojectapp.service.PhotographyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotographyServiceImpl implements PhotographyService {

    private final PhotographyRepository photographyRepository;

    public PhotographyServiceImpl(PhotographyRepository photographyRepository) {
        this.photographyRepository = photographyRepository;
    }

    @Override
    public void save(MultipartFile photography, String fileName, Designer designer) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, photography.getBytes());
            Photography photo = new Photography();
            photo.setFileName(photography.getOriginalFilename());
            photo.setDesigner(designer);
            photographyRepository.save(photo);
        }
    }

    @Override
    public void delete(long id) {
        photographyRepository.deleteByDesignerId(id);
    }

    @Override
    public Photography getByDesignerId(long id) {
        return photographyRepository.findByDesignerId(id);
    }
}
