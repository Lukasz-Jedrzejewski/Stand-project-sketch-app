package com.legion.standprojectapp.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "photography")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Photography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
    @ManyToOne
    private Designer designer;

    public Photography() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Photography(String fileName, String fileType, byte[] data, Designer designer) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.designer = designer;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", data=" + Arrays.toString(data) +
                ", designer=" + designer +
                '}';
    }
}