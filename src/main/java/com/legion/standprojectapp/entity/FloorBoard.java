package com.legion.standprojectapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "floor_board")
public class FloorBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "floorBoard", orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Project> projects;

    public FloorBoard() {
    }

    public FloorBoard(@NotBlank String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    @Override
    public String toString() {
        return "FloorBoard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public String toHtml() {
        return "<ul><li>nazwa = " + name + "</li></ul>";
    }
}
