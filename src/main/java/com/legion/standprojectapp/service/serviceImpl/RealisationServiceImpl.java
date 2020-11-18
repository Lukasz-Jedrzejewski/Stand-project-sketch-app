package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.repository.RealisationRepository;
import com.legion.standprojectapp.service.RealisationService;
import org.springframework.stereotype.Service;

@Service
public class RealisationServiceImpl implements RealisationService {

    private final RealisationRepository realisationRepository;

    public RealisationServiceImpl(RealisationRepository realisationRepository) {
        this.realisationRepository = realisationRepository;
    }
}
