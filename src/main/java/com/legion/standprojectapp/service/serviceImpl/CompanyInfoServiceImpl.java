package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.repository.CompanyInfoRepository;
import com.legion.standprojectapp.service.CompanyInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;

    public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    public List<CompanyInfo> findAll() {
        return companyInfoRepository.findAll();
    }
}
