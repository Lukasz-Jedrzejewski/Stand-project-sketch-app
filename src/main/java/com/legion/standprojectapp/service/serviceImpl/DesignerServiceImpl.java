package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.Designer;
import com.legion.standprojectapp.repository.DesignerRepository;
import com.legion.standprojectapp.service.DesignerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerServiceImpl implements DesignerService {

    private final DesignerRepository designerRepository;

    public DesignerServiceImpl(DesignerRepository designerRepository) {
        this.designerRepository = designerRepository;
    }

    @Override
    public List<Designer> findAll() {
        return designerRepository.findAll();
    }
}
