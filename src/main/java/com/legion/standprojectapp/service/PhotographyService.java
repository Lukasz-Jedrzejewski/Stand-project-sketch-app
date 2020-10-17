package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotographyService {
    void save(MultipartFile photography, String filename, Designer designer) throws IOException;
    void delete(long id);
    Photography getByDesignerId(long id);
}
