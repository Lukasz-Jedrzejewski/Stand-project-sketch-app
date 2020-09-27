package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import org.springframework.web.multipart.MultipartFile;

public interface PhotographyService {
    Photography save(MultipartFile file, Designer designer);
}
