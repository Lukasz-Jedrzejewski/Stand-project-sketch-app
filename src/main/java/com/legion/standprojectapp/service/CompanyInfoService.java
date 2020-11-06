package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CompanyInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CompanyInfoService {
    List<CompanyInfo> findAll();
    void save (CompanyInfo companyInfo);
    CompanyInfo getOne(long id);
    CompanyInfo edit (CompanyInfo companyInfo);
}
