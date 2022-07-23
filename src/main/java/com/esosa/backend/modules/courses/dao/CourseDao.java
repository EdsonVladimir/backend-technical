package com.esosa.backend.modules.courses.dao;


import com.esosa.backend.modules.courses.entities.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseDao {
    @Select("select * from operations.courses where status = 1 ORDER BY 1,2")
    List<Course> coursesList();
}
