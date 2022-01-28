package com.example.ex3mohamadamer.demo.controllers;
import com.example.ex3mohamadamer.demo.bean.UserValidation;
import com.example.ex3mohamadamer.demo.repository.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class Login {
    /**
     * Const Values for using in the login page
     */
    @Value("1234")
    private String Passwd;
    @Value("admin")
    private String userName;
    @Value("Username is incorrect")
    private String wrongUN;
    @Value("Password is incorrect")
    private String wrongPW;
    @Value("one or more inputs is empty")
    private String wrongEM;
    /**
     * User Validation to check if the user is online or not
     */
    @Resource(name = "sessionB")
    private UserValidation userValidation;

    /**
     *
     * @param request session use
     * @return page to load
     */
    @RequestMapping("/")
    public String handlePage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null && userValidation.isOnline()) {
           session = request.getSession(true);
            return "main";
        }
        else
            return "index";
    }
    /**
     *
     * @param user user data
     * @param model model to base data to then view
     * @param request session using
     * @return the page we want to load
     */
    @PostMapping("/loginPage")
    public String loginPage(User user, Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
            if (userValidation.isOnline() && session != null) {
                return "main";
            }
            if(user.getPassword().isEmpty() || user.getUserName().isEmpty()){
                model.addAttribute("wrong", wrongEM);
                return "index";
            }
            if (user.getUserName().equals(userName)) {
                if (user.getPassword().equals(Passwd)) {
                    userValidation.setOnline(true);
                    session = request.getSession(true);
                    return "main";
                } else {
                    model.addAttribute("wrong", wrongPW);
                    return "index";
                }

            } else {
                model.addAttribute("wrong", wrongUN);
                return "index";
            }
    }

}
