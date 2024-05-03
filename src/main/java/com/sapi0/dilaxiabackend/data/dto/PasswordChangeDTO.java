package com.sapi0.dilaxiabackend.data.dto;

public class PasswordChangeDTO {

    public String email;
    public String currentPassword;
    public String newPassword;

    public PasswordChangeDTO(String email, String currentPassword, String newPassword) {
        this.email = email;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public PasswordChangeDTO() {

    }

}
