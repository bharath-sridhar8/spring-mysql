package com.example.accessingdatamysql;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull(message = "email cannot be null")
    @Size(min = 7, max = 30)
    private String email;
}
