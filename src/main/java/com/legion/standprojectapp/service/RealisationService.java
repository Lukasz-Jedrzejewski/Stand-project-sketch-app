package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Realisation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RealisationService {

    void savePic (Realisation realisation, MultipartFile file) throws IOException;
    void deletePic (long id) throws IOException;
    List<Realisation> findAll();
    Realisation findOne (long id);
    void setImportant (long id);
    List<Realisation> findAllImportant();
}
