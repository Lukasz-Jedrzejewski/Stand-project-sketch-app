package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.entity.Realisation;
import com.legion.standprojectapp.repository.RealisationRepository;
import com.legion.standprojectapp.service.RealisationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RealisationServiceImpl implements RealisationService {

    private final RealisationRepository realisationRepository;

    public RealisationServiceImpl(RealisationRepository realisationRepository) {
        this.realisationRepository = realisationRepository;
    }

    @Override
    public void savePic(Realisation realisation, MultipartFile file) throws IOException {
        Path path = Paths.get(file.getOriginalFilename());
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, file.getBytes());
            realisation.setFileName(file.getOriginalFilename());
            realisation.setImportant(false);
            realisationRepository.save(realisation);
        }
    }

    @Override
    public void deletePic(long id) throws IOException {
        Path path = Paths.get(findOne(id).getFileName());
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Override
    public List<Realisation> findAll() {
        return realisationRepository.findAll();
    }

    @Override
    public Realisation findOne(long id) {
        return realisationRepository.getOne(id);
    }

    @Override
    public void setImportant(long id) {
        Realisation current = findOne(id);
        current.setImportant(true);
        realisationRepository.save(current);
    }

    @Override
    public List<Realisation> findAllImportant() {
        return realisationRepository.findAllByImportantTrue();
    }
}
