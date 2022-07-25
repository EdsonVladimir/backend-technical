package com.esosa.backend.modules.utilitarian.dao;

import com.esosa.backend.modules.utilitarian.entities.EducationLevel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationLevelDao {
    @Select("SELECT id_education_level, name FROM utilitarian.education_level")
    List<EducationLevel> educationLevelList();
    @Insert("insert into utilitarian.education_level(name) values (#{name})")
    void createEducationLevel(EducationLevel data);
    @Update("update utilitarian.education_level set name=#{name} where id_education_level=#{id_education_level}")
    void updateEducationLevel(EducationLevel data);
    @Delete("update utilitarian.education_level set status=0 where id_education_level=#{id_education_level}")
    void deleteEducationLevel(Long id);
}
