package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Realization;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RealizationService {

    void savePic (Realization realization, MultipartFile file, String fileName) throws IOException;
    void deletePic (long id) throws IOException;
    List<Realization> findAll();
    Realization findOne (long id);
    void setImportant (long id);
    List<Realization> findAllImportant();
}
