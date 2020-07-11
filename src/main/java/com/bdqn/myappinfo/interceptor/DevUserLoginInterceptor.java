package com.bdqn.myappinfo.interceptor;

import com.bdqn.myappinfo.tools.MyConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class DevUserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object devUserSession = session.getAttribute("devUserSession");

        System.out.println("-----ContextPath-----:"+request.getContextPath());
        System.out.println("-----Method-----:"+request.getMethod());
        System.out.println("-----RequestURI-----:"+request.getRequestURI());

        if(devUserSession == null || devUserSession.equals("")){
            System.out.println("-------Go Other Place---------");
            response.sendRedirect("http://localhost:8888/dev/login?error="+ MyConstant.LOGIN_EXPIRED_ERROR);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
