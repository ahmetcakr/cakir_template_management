package com.cakir.templateManagement.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    public String firstname;
    public String lastname;
    public String email;
    public String password;

    public RegisterRequestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterRequestDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public RegisterRequestDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public RegisterRequestDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }



    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }




}
