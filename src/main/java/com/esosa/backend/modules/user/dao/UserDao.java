package com.esosa.backend.modules.user.dao;

import com.esosa.backend.modules.user.entities.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT id_user, full_name FROM core.user")
    List<User> usersList();
    @Insert("insert into core.user(full_name,email,id_country,pass) values (#{full_name},#{email},#{id_country},#{pass})")
    void createUserInit(UserRegInit data);
    @Update("update core.user set full_name=#{full_name},email=#{email},id_country=#{id_country} where id_user=#{id_user}")
    void updateUser(User data);
    @Delete("update core.user set status=0 where id_user=#{id}")
    void deleteUser(Long id);

    @Select("select id_user,email,pass from core.user where status=1 AND email=#{email}")
    UserLogin user(String email);
    @Select("select id_user, full_name,email,id_country from core.user where status=1 AND email=#{email}")
    UserRes userRes(String email);

    @Update("update core.user set date_birth=#{date_birth},gender=#{gender}, address=#{address}, phone_number=#{phone_number}, id_language=#{id_language}, id_education=#{id_education} where id_user=#{id_user}")
    void updateUserCourse(UpdateUser data);
}
