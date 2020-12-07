package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.CompanyInfo;
import com.legion.standprojectapp.repository.CompanyInfoRepository;
import com.legion.standprojectapp.service.CompanyInfoService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    final String defaultLogo = "pexels-daniel-nettesheim-1162361.jpg";

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
    public void addLogo(MultipartFile logo) throws IOException {
        if (Objects.equals(logo.getContentType(), "image/jpg")
                || Objects.equals(logo.getContentType(), "image/jpeg")) {
            Path path = Paths.get("src/main/webapp/resources/images/logo/company-logo.jpg");
            if (Files.exists(path)) {
                FileUtils.cleanDirectory(new File("src/main/webapp/resources/images/logo/"));
            }
            Files.createFile(path);
            Files.write(path, logo.getBytes());
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
