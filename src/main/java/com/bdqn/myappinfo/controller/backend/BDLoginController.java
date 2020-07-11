package com.bdqn.myappinfo.controller.backend;

import com.bdqn.myappinfo.pojo.BackendUser;
import com.bdqn.myappinfo.service.IBackendUserService;
import com.bdqn.myappinfo.tools.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class BDLoginController {

    @Autowired
    private IBackendUserService backendUserService;

    //登录页面（如之前登录过直接跳过）
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, @RequestParam(value = "error", required = false)String error) throws Exception{
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        if(error != null) {
            if (error.equals(MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR)) {
                mv.addObject("error", MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR_STRING);
            } else if (error.equals(MyConstant.LOGIN_EXPIRED_ERROR)) {
                mv.addObject("error", MyConstant.LOGIN_EXPIRED_ERROR_STRING);
            }
        }
        if(session.getAttribute("userSession") != null){
            mv.addObject("userSession", session.getAttribute("userSession"));
            mv.setViewName("backend/main");
        }else{
            mv.setViewName("backendlogin");
        }
        return mv;
    }

    //登录操作，直接保存登录状态7天，每次登录刷新
    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request, @RequestParam("userCode")String username, @RequestParam("userPassword")String password) throws Exception{
        BackendUser backendUser = backendUserService.login(username, password);

        if(backendUser == null){
            return "redirect:login?error=" + MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR;
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(7*24*60*60);

        session.setAttribute("userSession", backendUser);
        return "redirect:http://localhost:8888/manager/backend/main";
    }

    //注销操作
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        if(session.getAttribute("userSession") != null){
            session.setAttribute("userSession", null);
        }
        return "redirect:http://localhost:8888";
    }
}
