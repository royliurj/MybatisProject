package com.roy.simple.dao;

import com.roy.simple.model.Country;
import org.apache.ibatis.annotations.Select;

public interface CountryMapperAnnotation {

    @Select("select * from country where id = #{id}")
    public Country getCountryById(Integer id);
}
