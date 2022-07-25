package com.esosa.backend.modules.utilitarian.dao;

import com.esosa.backend.modules.utilitarian.entities.Language;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LanguageDao {
    @Select("SELECT id_language, name FROM utilitarian.language")
    List<Language> languageList();
    @Insert("insert into utilitarian.language(name) values (#{name})")
    void createLanguage(Language data);
    @Update("update utilitarian.language set name=#{name} where id_language=#{id_language}")
    void updateLanguage(Language data);
    @Delete("update utilitarian.language set status=0 where id_language=#{id_language}")
    void deleteLanguage(Long id);
}
