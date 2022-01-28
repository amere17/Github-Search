package com.example.ex3mohamadamer.demo.controllers;

import com.example.ex3mohamadamer.demo.bean.UserValidation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handle Error Pages
 */
@Controller
public class ErrorContoller implements ErrorController {

    @Resource(name = "sessionB")
    UserValidation userValidation;
    /**
     * handle errors
     * @param request session use
     * @return name of the page to load
     */
    @RequestMapping("/error")
        public String handleError(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            if(session!=null && userValidation.isOnline() ) {
                session = request.getSession(true);
                return "main";
            }
            else
                return "index";
        }

    /**
     *
     * @return getErrorPath
     */
    @Override
        public String getErrorPath() {
            return "/error";
        }

}
