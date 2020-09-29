package com.legion.standprojectapp.service;

import com.legion.standprojectapp.entity.Designer;

import java.util.List;

public interface DesignerService {
    List<Designer> findAll();
    Designer getOne(long id);
    void save(Designer designer);
    void edit(Designer designer);
}
