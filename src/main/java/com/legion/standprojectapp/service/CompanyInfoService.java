package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {
    List<CompanyInfo> findAll();
    void save (CompanyInfo companyInfo);
}
