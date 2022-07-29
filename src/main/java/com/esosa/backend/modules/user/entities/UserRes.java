package com.esosa.backend.modules.user.entities;
import lombok.Data;
@Data
public class UserRes {
    private static final long serialVersionUID = 1L;
    private int id_user;
    private String full_name;
    private String email;
    private int id_country;
}
