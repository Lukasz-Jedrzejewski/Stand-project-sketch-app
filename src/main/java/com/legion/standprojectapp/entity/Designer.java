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
    private String description;
    @OneToOne
    @JoinColumn(name = "photography_id")
    private Photography photography;

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

    public Photography getPhotography() {
        return photography;
    }

    public void setPhotography(Photography photography) {
        this.photography = photography;
    }
}
