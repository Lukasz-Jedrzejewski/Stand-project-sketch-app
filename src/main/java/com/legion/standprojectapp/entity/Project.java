package com.legion.standprojectapp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Pattern(regexp = "\\d+|\\d+\\.\\d+")
    private String width;
    @NotNull
    @Pattern(regexp = "\\d+|\\d+\\.\\d+")
    private String depth;
    @NotNull
    @ManyToOne
    private TypeOfBuilding typeOfBuilding;
    private boolean withFloor;
    private boolean withHanger;
    @NotNull
    @Pattern(regexp = "\\d+|\\d+\\.\\d+")
    private String standHeight;
    private boolean utilityRoom;
    private boolean vipRoom;
    @NotNull
    @ManyToOne
    private FloorBoard floorBoard;
    @NotNull
    @Max(4)
    private int walls;
    @NotNull
    @ManyToOne
    private Branch branch;
    @NotBlank
    private String companyName;
    @Email
    @NotBlank
    private String companyMail;
    private LocalDateTime created;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public TypeOfBuilding getTypeOfBuilding() {
        return typeOfBuilding;
    }

    public void setTypeOfBuilding(TypeOfBuilding typeOfBuilding) {
        this.typeOfBuilding = typeOfBuilding;
    }

    public boolean isWithFloor() {
        return withFloor;
    }

    public void setWithFloor(boolean withFloor) {
        this.withFloor = withFloor;
    }

    public boolean isWithHanger() {
        return withHanger;
    }

    public void setWithHanger(boolean withHanger) {
        this.withHanger = withHanger;
    }

    public String getStandHeight() {
        return standHeight;
    }

    public void setStandHeight(String standHeight) {
        this.standHeight = standHeight;
    }

    public boolean isUtilityRoom() {
        return utilityRoom;
    }

    public void setUtilityRoom(boolean utilityRoom) {
        this.utilityRoom = utilityRoom;
    }

    public boolean isVipRoom() {
        return vipRoom;
    }

    public void setVipRoom(boolean vipRoom) {
        this.vipRoom = vipRoom;
    }

    public FloorBoard getFloorBoard() {
        return floorBoard;
    }

    public void setFloorBoard(FloorBoard floorBoard) {
        this.floorBoard = floorBoard;
    }

    public int getWalls() {
        return walls;
    }

    public void setWalls(int walls) {
        this.walls = walls;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }
}
