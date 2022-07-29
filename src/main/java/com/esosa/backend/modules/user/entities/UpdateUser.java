package com.esosa.backend.modules.user.entities;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUser {
    private static final long serialVersionUID = 1L;
    private Long id_user;
    private Date date_birth;
    private String gender;
    private String address;
    private String phone_number;
    private int id_language;
    private int id_education;
}
