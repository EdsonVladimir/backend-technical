package com.esosa.backend.modules.operations.entities;
import lombok.Data;
@Data
public class EnrollerCourseCreate {
    private static final long serialVersionUID = 1L;
    private int id_course;
    private int id_user;
    private int id_payment_plan;
}
