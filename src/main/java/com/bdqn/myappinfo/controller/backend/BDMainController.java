package com.bdqn.myappinfo.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager/backend/main")
public class BDMainController {

    @RequestMapping("")
    public ModelAndView main(HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        Object userSession = session.getAttribute("userSession");
        if(userSession != null){
            mv.addObject("userSession", userSession);
        }
        mv.setViewName("backend/main");
        return mv;
    }

}
