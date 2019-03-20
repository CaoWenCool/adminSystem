package com.demo.adminsystem.core.interceptors;

import com.demo.adminsystem.core.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 15:09
 * @version: V1.0
 * @detail:
 **/
@Configuration
public class AdminLoginInterceptor implements HandlerInterceptor{

    final static Logger LOGGER = LoggerFactory.getLogger(AdminLoginInterceptor.class);

    /**
     * 页面渲染完以后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * Controller调用后，渲染页面前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);


    }

    /**
     * Controller前调用的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }
}
