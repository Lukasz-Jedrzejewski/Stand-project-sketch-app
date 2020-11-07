package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.repository.CompanyInfoRepository;
import com.legion.standprojectapp.service.CompanyInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Override
    public void save(CompanyInfo companyInfo) {
        List<CompanyInfo> list = companyInfoRepository.findAll();
        if (list.size() < 1) {
            companyInfoRepository.save(companyInfo);
        }
    }

    @Override
    public void addLogo(CompanyInfo companyInfo, String fileName, MultipartFile logo) throws IOException {
        CompanyInfo companyFromDB = companyInfoRepository.getOne(companyInfo.getId());
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.write(path, logo.getBytes());
            companyFromDB.setLogoName(fileName);
            companyInfoRepository.save(companyFromDB);
        }
    }

    @Override
    public CompanyInfo getOne(long id) {
        return companyInfoRepository.getOne(id);
    }

    @Override
    public CompanyInfo edit(CompanyInfo companyInfo) {
        return companyInfoRepository.save(companyInfo);
    }
}
