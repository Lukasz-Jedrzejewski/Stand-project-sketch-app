package com.legion.standprojectapp.service.serviceImpl;

import com.legion.standprojectapp.entity.File;
import com.legion.standprojectapp.entity.Project;
import com.legion.standprojectapp.repository.FileRepository;
import com.legion.standprojectapp.service.serviceImpl.FileServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class FileServiceImplTest {



    @InjectMocks
    FileServiceImpl fileService;

    @Mock
    FileRepository fileRepository;

    private static final int ID = 1;

    @Before
    public void setUp() {
        this.fileService = new FileServiceImpl(fileRepository);
    }

    @Test
    void save() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        Project project = mock(Project.class);
        File newFile = new File(file.getOriginalFilename(), file.getContentType(), file.getBytes(), project);
        fileService.save(file, project);
        verify(fileRepository).save(newFile);
    }

    @Test
    void readFiles() {
        List<File> files = fileService.readFiles();
        assertNotNull(files);
    }

    @Test
    void getFile() {
        fileService.getFile(ID);
        verify(fileRepository).getOne(ID);
    }

    @Test
    void readAllByProjectId() {
        fileService.readAllByProjectId(ID);
        verify(fileRepository).findAllByProjectId(ID);
    }
}