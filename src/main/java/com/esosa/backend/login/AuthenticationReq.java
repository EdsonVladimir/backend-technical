package com.esosa.backend.login;

import java.io.Serializable;

public class AuthenticationReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String pass;

    public AuthenticationReq(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

