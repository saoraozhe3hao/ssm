package com.hogen.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;  // 返回 false 则直接结束
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response , Object handler ,ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response ,Object handler , Exception ex ) throws Exception {

    }
    // 生命周期 preHandle -> servlet处理 -> postHandle -> 处理jsp -> afterCompletion
    // 多个拦截器时，preHandle按配置顺序执行，postHandle逆序执行，afterCompletion逆序执行
    // 多个拦截器时，一个preHandle返回false，则除了preHandle已经返回true的拦截器会执行它的afterCompletion，其他都不执行
}
