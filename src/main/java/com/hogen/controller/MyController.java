package com.hogen.controller;

import com.hogen.frontParam.FrontParam;
import com.hogen.service.CityServiceImpl;
import com.hogen.sqlResult.City;
import com.hogen.validator.CityValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping ("/my")
public class MyController {
    static Logger logger = LogManager.getLogger(MyController.class.getName());

    @Autowired
    private CityServiceImpl cityServiceImpl;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @InitBinder
    public void initBinder(DataBinder binder ) {
        binder.setValidator(new CityValidator());
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("username", "password");
        subject.login(token);

        mv.addObject ("success","dd");
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    @RequiresRoles({"role_1", "role_2"})
    @RequiresPermissions("admin")
    @RequestMapping(value="/list", method=RequestMethod.GET)
    @ResponseBody
    public String register(){
        return "has permission";
    }
}