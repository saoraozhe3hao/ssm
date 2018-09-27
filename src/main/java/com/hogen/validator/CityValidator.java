package com.hogen.validator;

import com.hogen.frontParam.FrontParam;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CityValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FrontParam.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FrontParam fontParam = (FrontParam) target;
        errors.rejectValue("cityName", null ,"业务校验错误");
    }
}
