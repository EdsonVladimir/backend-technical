package com.esosa.backend.modules.courses.dao;

import com.esosa.backend.modules.courses.entities.Course;
import com.esosa.backend.modules.courses.entities.CourseOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CourseOperationDao {


    @Insert("insert into operations.courses(name, description, img, id_user_teacher,duration_time, price, date_init, date_end, call_number) values (#{name}, #{description}, #{img}, #{id_user_teacher},#{duration_time}, #{price}, #{date_init}, #{date_end}, #{call_number})")
    void createCourse(CourseOperation data);
    @Update("update operations.courses set name=#{name}, description=#{description}, id_user_teacher=#{id_user_teacher},duration_time=#{duration_time}, price=#{price}, date_init=#{date_init}, date_end=#{date_end}, call_number=#{call_number} where id_course=#{id_course}")
    void updateCourse(Course data);
    @Delete("update operations.courses set status=0 where id_course=#{id}")
    void deleteCourse(Long id);
}
