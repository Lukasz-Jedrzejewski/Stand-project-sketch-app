package com.legion.standprojectapp.controller;

import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.service.serviceImpl.FileServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileServiceImpl fileService;

    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id) {
        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getFileName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping("/display/{id}")
    public void displayFile(@PathVariable int id, HttpServletResponse response) throws IOException {
        File file = fileService.getFile(id);

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(file.getData());

        response.getOutputStream().close();

    }


}
