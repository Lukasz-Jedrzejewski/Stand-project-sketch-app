package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.entity.Designer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DesignerService {
    List<Designer> findAll();
    Designer getOne(long id);
    void save(Designer designer);
    void edit(Designer designer);
    void deleteDesigner(long id);
    void deletePic (String fileName) throws IOException;
    String gePicNameByDesignerId (long id);
    void addPic (Designer designer, String fileName, MultipartFile picture) throws IOException;
    void clearPic(long id, String filename) throws IOException;
}
