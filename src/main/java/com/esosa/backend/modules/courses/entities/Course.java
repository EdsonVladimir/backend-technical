package com.esosa.backend.modules.courses.entities;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id_course;
    private String name;
    private String description;
    private String img;
    private int id_user_teacher;
    private String duration_time;
    private double price;
    private Date date_init;
    private Date date_end;
    private String call_number;

}
