package com.bdqn.myappinfo.controller.developer;

import com.bdqn.myappinfo.pojo.DevUser;
import com.bdqn.myappinfo.service.IDevUserService;
import com.bdqn.myappinfo.tools.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dev")
public class DevLoginController {

    @Autowired
    private IDevUserService devUserService;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, @RequestParam(value = "error", required = false)String error) throws Exception{
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        Object devSession = session.getAttribute("devUserSession");

        if(devSession != null){
            mv.setViewName("developer/main");
            return mv;
        }
        if(error != null) {
            if (error.equals(MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR)) {
                mv.addObject("error", MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR_STRING);
            } else if (error.equals(MyConstant.LOGIN_EXPIRED_ERROR)) {
                mv.addObject("error", MyConstant.LOGIN_EXPIRED_ERROR_STRING);
            }
        }

        mv.setViewName("devlogin");
        return mv;
    }

    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request, @RequestParam("devCode")String devCode, @RequestParam("devPassword")String devPassword) throws Exception{
        DevUser login = devUserService.login(devCode, devPassword);
        if(login == null){
            return "redirect:login?error=" + MyConstant.USER_MISSING_OR_WRONG_PASSWORD_ERROR;
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("devUserSession", login);
            session.setMaxInactiveInterval(7*24*60*60);
            return "redirect:http://localhost:8888/dev/flatform/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        Object devUserSession = session.getAttribute("devUserSession");

        if(devUserSession != null){
            session.setAttribute("devUserSession", null);
        }

        return "redirect:http://localhost:8888/";
    }

}
