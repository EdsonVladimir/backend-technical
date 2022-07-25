package com.esosa.backend.modules.utilitarian.dao;

import com.esosa.backend.modules.utilitarian.entities.Country;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CountryDao {
    @Select("SELECT id_country, name FROM utilitarian.country")
    List<Country> countryList();
    @Insert("insert into utilitarian.country(name) values (#{name})")
    void createCountry(Country data);
    @Update("update utilitarian.country set name=#{name} where id_country=#{id_country}")
    void updateCountry(Country data);
    @Delete("update utilitarian.country set status=0 where id_country=#{id_country}")
    void deleteCountry(Long id);
}
