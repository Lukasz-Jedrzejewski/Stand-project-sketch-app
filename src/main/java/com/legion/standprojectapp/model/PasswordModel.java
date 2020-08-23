package com.legion.standprojectapp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

public class PasswordModel {
    @NotBlank(groups = Default.class)
    private String confirmPassword;

    public PasswordModel() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
