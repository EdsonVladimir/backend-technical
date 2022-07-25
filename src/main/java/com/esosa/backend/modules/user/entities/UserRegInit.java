package com.esosa.backend.modules.user.entities;

import lombok.Data;
@Data
public class UserRegInit {
    private static final long serialVersionUID = 1L;
    private String full_name;
    private String email;
    private int id_country;
    private String pass;
}
