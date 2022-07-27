package com.esosa.backend.modules.user.entities;
import lombok.Data;
@Data
public class UserLogin {
    private static final long serialVersionUID = 1L;
    private String email;
    private String pass;
}
