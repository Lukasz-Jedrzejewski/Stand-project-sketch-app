package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Realization;
import com.legion.standprojectapp.repository.RealizationRepository;
import com.legion.standprojectapp.service.RealizationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class RealizationServiceImpl implements RealizationService {

    private final RealizationRepository realisationRepository;

    public RealizationServiceImpl(RealizationRepository realisationRepository) {
        this.realisationRepository = realisationRepository;
    }

    @Override
    public void savePic(Realization realization, MultipartFile file, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, file.getBytes());
            realization.setFileName(file.getOriginalFilename());
            realization.setImportant(false);
            realisationRepository.save(realization);
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
    public List<Realization> findAll() {
        return realisationRepository.findAll();
    }

    @Override
    public Realization findOne(long id) {
        return realisationRepository.getOne(id);
    }

    @Override
    public void setImportant(long id) {
        Realization current = findOne(id);
        current.setImportant(true);
        realisationRepository.save(current);
    }

    @Override
    public List<Realization> findAllImportant() {
        return realisationRepository.findAllByImportantTrue();
    }
}
