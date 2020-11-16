package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.repository.DesignerRepository;
import com.legion.standprojectapp.service.DesignerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DesignerServiceImpl implements DesignerService {

    final String defaultPicture = "pexels-mohamed-abdelgaffar-771742.jpg";

    private final DesignerRepository designerRepository;

    public DesignerServiceImpl(DesignerRepository designerRepository) {
        this.designerRepository = designerRepository;
    }

    @Override
    public List<Designer> findAll() {
        return designerRepository.findAll();
    }

    @Override
    public Designer getOne(long id) {
        return designerRepository.getOne(id);
    }

    @Override
    public void save(Designer designer) {
        if (designer.getPhotoName() == null) {
            designer.setPhotoName(defaultPicture);
        }
        designerRepository.save(designer);
    }

    @Override
    public void edit(Designer designer) {
        Designer designerFromDB = designerRepository.getOne(designer.getId());
        designerFromDB.setName(designer.getName());
        designerFromDB.setSurname(designer.getSurname());
        designerFromDB.setDescription(designer.getDescription());
        designerRepository.save(designerFromDB);
    }

    @Override
    public void deleteDesigner(long id) {
        designerRepository.delete(designerRepository.getOne(id));
    }

    @Override
    public void deletePic(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (!fileName.equals("src/main/webapp/resources/images/" + defaultPicture)) {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
    }

    @Override
    public String gePicNameByDesignerId(long id) {
        return designerRepository.getPhotoName(id);
    }

    @Override
    public void addPic(Designer designer, String fileName, MultipartFile picture) throws IOException {
        Designer designerFromDB = designerRepository.getOne(designer.getId());
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, picture.getBytes());
            designerFromDB.setPhotoName(picture.getOriginalFilename());
            designerRepository.save(designer);
        }
    }

    @Override
    public void clearPic(long id, String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!filename.equals("src/main/webapp/resources/images/" + defaultPicture)) {
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
        Designer designerFromDB = designerRepository.getOne(id);
        designerFromDB.setPhotoName(defaultPicture);
        designerRepository.save(designerFromDB);
    }
}
