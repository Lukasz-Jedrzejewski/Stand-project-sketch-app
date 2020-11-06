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
import java.util.List;

@Service
public class PhotographyServiceImpl implements PhotographyService {

    final String defaultPicture = "pexels-mohamed-abdelgaffar-771742.jpg";

    private final PhotographyRepository photographyRepository;

    public PhotographyServiceImpl(PhotographyRepository photographyRepository) {
        this.photographyRepository = photographyRepository;
    }

    @Override
    public void save(MultipartFile photography, String fileName, Designer designer) throws IOException {
        Photography photoFromDB = getByDesignerId(designer.getId().intValue());
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, photography.getBytes());
            photoFromDB.setFileName(photography.getOriginalFilename());
            photoFromDB.setDesigner(designer);
            photographyRepository.save(photoFromDB);
        }
    }

    @Override
    public void delete(long id, String filename) throws IOException {
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        photographyRepository.deleteByDesignerId(id);
    }

    @Override
    public void deletePic(long id, String filename) throws IOException {
        Path path = Paths.get(filename);
        System.out.println(filename);
        System.out.println(defaultPicture);
        if (!filename.equals("src/main/webapp/resources/images/" + defaultPicture)) {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
    }

    @Override
    public void setDefaultPhotography(Designer designer) {
        Photography photography = new Photography();
        photography.setFileName(defaultPicture);
        photography.setDesigner(designer);
        photographyRepository.save(photography);
    }

    @Override
    public Photography getByDesignerId(long id) {
        return photographyRepository.findByDesignerId(id);
    }

    @Override
    public boolean existsByDesignerId(long id) {
        return photographyRepository.existsPhotographyByDesignerId(id);
    }

    @Override
    public List<Photography> findAll() {
        return photographyRepository.findAll();
    }
}
