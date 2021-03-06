package com.legion.standprojectapp.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public File() {
    }

    public File(String fileName, String fileType, byte[] data, Project project) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.project = project;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", project=" + project +
                '}';
    }
}
