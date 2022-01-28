package com.example.ex3mohamadamer.demo.controllers;

import com.example.ex3mohamadamer.demo.bean.UserValidation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Logout Controller
 */
@Controller
public class Logout {
    @Resource(name = "sessionB")
    private UserValidation userValidation;

    /**
     *
     * @param request session use
     * @return page to load
     */
    @PostMapping("/logout")
    public String LogoutSession(HttpServletRequest request) {
      try {
          request.getSession().invalidate();
          userValidation.setOnline(false);
          return "index";
      }catch (Exception e){
          return "main";
      }
    }
}
