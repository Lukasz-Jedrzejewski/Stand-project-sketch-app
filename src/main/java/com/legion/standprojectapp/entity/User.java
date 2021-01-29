package com.legion.standprojectapp.entity;

import com.legion.standprojectapp.validation.groups.UserEditValidationGroup;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(groups = {Default.class, UserEditValidationGroup.class})
    private String companyName;
    @NotBlank(groups = {Default.class, UserEditValidationGroup.class})
    @Email(groups = {Default.class, UserEditValidationGroup.class})
    @Column(nullable = false, unique = true, length = 60)
    private String companyMail;
    @NotBlank(groups = Default.class)
    private String password;
    private boolean enabled;
    private boolean admin;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank(groups = {Default.class, UserEditValidationGroup.class}) String companyName,
                @NotBlank(groups = {Default.class, UserEditValidationGroup.class}) @Email(groups = {Default.class, UserEditValidationGroup.class}) String companyMail,
                @NotBlank(groups = Default.class) String password) {
        this.companyName = companyName;
        this.companyMail = companyMail;
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String toHtml() {
        return "<ul><li>nazwa klienta = " + companyName + "</li><li>mail klienta = " + companyMail + "</li></ul>";
    }
}
