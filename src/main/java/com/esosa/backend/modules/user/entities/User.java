package com.esosa.backend.modules.user.entities;
import java.io.Serializable;
import lombok.Data;
@Data
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id_user;
    private String full_name;
    private String email;
    private int id_country;
    private int status;
    private String date_reg;
    private String date_modified;
    private int id_rol;
    private String date_bird;
    private String gender;
    private String address;
    private String phone_number;
    private int id_language;
    private int id_education;
}
