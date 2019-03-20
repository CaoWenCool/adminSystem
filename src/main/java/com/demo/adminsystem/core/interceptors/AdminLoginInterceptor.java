package com.demo.adminsystem.core.interceptors;

import com.demo.adminsystem.core.util.RequestUtil;
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
public class AdminLoginInterceptor implements HandlerInterceptor{

    /**
     * Controller 前调用的方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = (Integer) request.getAttribute("userId");
        if(userId == null){
            response.sendRedirect(RequestUtil.getAppURL(request)+ "/login.html");
            return false;
        }
        return true;
    }

    /**
     * Controller 调用后，渲染也页面之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    /**
     * 页面渲染以后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
