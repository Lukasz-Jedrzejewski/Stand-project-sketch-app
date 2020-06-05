package com.legion.standprojectapp.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.function.LongToIntFunction;

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
    @ManyToOne
    @JoinColumn(name="type_of_building_id")
    @Convert(converter = LongToIntFunction.class)
    private TypeOfBuilding typeOfBuilding;
    private boolean withFloor;
    private boolean withHanger;
    @NotNull
    @Pattern(regexp = "\\d+|\\d+\\.\\d+")
    private String standHeight;
    private boolean utilityRoom;
    private boolean vipRoom;
    @ManyToOne
    @JoinColumn(name="floor_board_id")
    @Convert(converter = LongToIntFunction.class)
    private FloorBoard floorBoard;
    @NotNull
    @Max(4)
    private int walls;
    @ManyToOne
    @JoinColumn(name="branch_id")
    @Convert(converter = LongToIntFunction.class)
    private Branch branch;
    private String companyName;
    @Email
    private String companyMail;
    private LocalDate created;
    @OneToMany(mappedBy = "project")
    private List<File> files;

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", width='" + width + '\'' +
                ", depth='" + depth + '\'' +
                ", withFloor=" + withFloor +
                ", withHanger=" + withHanger +
                ", standHeight='" + standHeight + '\'' +
                ", utilityRoom=" + utilityRoom +
                ", vipRoom=" + vipRoom +
                ", walls=" + walls +
                ", companyName='" + companyName + '\'' +
                ", companyMail='" + companyMail + '\'' +
                ", created=" + created +
                '}';
    }

    public String toHtml() {
        return "<section><ul><li>szerokość stoiska = " + width + "</li>" +
                "<li>głębokość stoiska = " + depth + "</li>" +
                "<li>rodzaj zabudowy = " + typeOfBuilding.getName() +"</li>" +
                "<li>piętro = " + withFloor +"</li>" +
                "<li>podwieszenie = " + withHanger +"</li>" +
                "<li>wysokość stoiska = " + standHeight + "</li>" +
                "<li>zaplecze użytkowe = " + utilityRoom +"</li>" +
                "<li>vip room = " + vipRoom +"</li>" +
                "<li>podłoga = " + floorBoard.getName() +"</li>" +
                "<li>ilość ścian = " + walls +"</li>" +
                "<li>nazwa klienta = " + companyName + "</li>" +
                "<li>mail klienta = " + companyMail + "</li>" +
                "<li>data utworzenia = " + created +"</li>" +
                "</ul></section>";
    }
}
