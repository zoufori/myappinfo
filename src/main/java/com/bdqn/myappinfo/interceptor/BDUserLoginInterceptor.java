package com.bdqn.myappinfo.interceptor;

import com.bdqn.myappinfo.pojo.BackendUser;
import com.bdqn.myappinfo.tools.MyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class BDUserLoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BDUserLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object devUserSession = request.getSession().getAttribute("userSession");

        logger.info("-------" + request.getContextPath() + "--------");
        logger.info("-------" + request.getMethod() + "--------");
        logger.info("-------" + request.getRequestURI() + "--------");

        if(devUserSession instanceof BackendUser){
            return true;
        }else{
            response.sendRedirect("http://localhost:8888/manager/login?error="+ MyConstant.LOGIN_EXPIRED_ERROR);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
