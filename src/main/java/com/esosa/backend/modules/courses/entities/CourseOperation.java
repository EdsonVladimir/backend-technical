package com.esosa.backend.modules.courses.entities;

import lombok.Data;

import java.util.Date;

@Data
public class CourseOperation {
    private static final long serialVersionUID = 1L;
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
