package com.legion.standprojectapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "designer")
public class Designer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne(mappedBy = "designer")
    private Photography photo;

    public Designer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photography getPhoto() {
        return photo;
    }

    public void setPhoto(Photography photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Designer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
