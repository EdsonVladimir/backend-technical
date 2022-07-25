package com.esosa.backend.modules.operations.dao;

import com.esosa.backend.modules.operations.entities.EnrolledCourse;
import com.esosa.backend.modules.operations.entities.EnrolledCourseUser;
import com.esosa.backend.modules.operations.entities.EnrollerCourseCreate;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnrolledCourseDao {
    @Select("SELECT id_enrolled_courses, id_course as foreign,id_user, id_payment_plan FROM operations.enrolled_courses where id_user=#{id}")
    @Results(value = {
            @Result(property = "course", column = "foreign", one = @One(select = "com.esosa.backend.modules.courses.dao.CourseDao.course")),
    })
    List<EnrolledCourseUser> enrolledCourseList(Long id);
    @Insert("insert into operations.enrolled_courses(id_course,id_user, id_payment_plan) values (#{id_course}, #{id_user},#{id_payment_plan})")
    void createEnrolledCourse(EnrollerCourseCreate data);
    @Update("update operations.enrolled_courses set id_course=#{id_course}, discount=#{discount}  where id_enrolled_courses=#{id_enrolled_courses}")
    void updateEnrolledCourse(EnrolledCourse data);
    @Delete("update operations.enrolled_courses set status=0 where id_enrolled_courses=#{id_enrolled_courses}")
    void deleteEnrolledCourse(Long id);
}
