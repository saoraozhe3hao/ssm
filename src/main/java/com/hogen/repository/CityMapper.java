package com.hogen.repository;

import com.hogen.frontParam.FrontParam;
import com.hogen.sqlResult.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMapper {
    public City getCity(long id);
}