package com.esosa.backend.modules.user.dao;

import com.esosa.backend.modules.user.entities.User;
import com.esosa.backend.modules.user.entities.UserRegInit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT id_user, full_name FROM core.user")
    List<User> usersList();
    @Insert("inser into course.user(full_name,email,id_country,date_reg,pass) values (full_name=#{full_name},email=#{email},id_country=#{id_country},date_reg=#{date_reg},pass=#{pass})")
    List<UserRegInit> usersCreateInit(UserRegInit data);

}
