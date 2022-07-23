package com.esosa.backend.modules.user.dao;

import java.io.*;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.esosa.backend.modules.user.entities.User;

@Mapper
public interface UserDao {
    @Select("SELECT id_user, full_name FROM core.user")
    List<User> usersList();


}
