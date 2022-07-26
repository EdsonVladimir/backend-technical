package com.esosa.backend.modules.user.dao;

import com.esosa.backend.modules.user.entities.User;
import com.esosa.backend.modules.user.entities.UserRegInit;
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
}
