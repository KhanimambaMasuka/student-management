package com.learning.dto;

import java.io.Serial;


public class UserDTO extends DTO {
    @Serial
    private static final long serialVersionUID = 1440738994716653783L;

    private Long id;
    private String fullName;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
