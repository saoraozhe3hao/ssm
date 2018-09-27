package com.hogen.component;

import com.hogen.frontParam.FrontParam;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value ;

@Component
public class Country implements BeanNameAware {
    @Value("1")
    private Long id;
    @Value("China")
    private String countryName ;
    private FrontParam city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public FrontParam getCity() {
        return city;
    }

    public void setCity(FrontParam city) {
        this.city = city;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }
}
