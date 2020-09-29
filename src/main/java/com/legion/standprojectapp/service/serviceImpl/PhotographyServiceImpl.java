package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.entity.Photography;
import com.legion.standprojectapp.repository.PhotographyRepository;
import com.legion.standprojectapp.service.PhotographyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotographyServiceImpl implements PhotographyService {

    private final PhotographyRepository photographyRepository;

    public PhotographyServiceImpl(PhotographyRepository photographyRepository) {
        this.photographyRepository = photographyRepository;
    }

    @Override
    public Photography save(MultipartFile photography, Designer designer) {
        String fileName = photography.getOriginalFilename();
        try {
            Photography newFile = new Photography(fileName, photography.getContentType(), photography.getBytes(), designer);
            return photographyRepository.save(newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(long id) {
        photographyRepository.deleteByDesignerId(id);
    }
}
