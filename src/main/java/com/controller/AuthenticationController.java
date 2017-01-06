package com.controller;

import com.dao.AuthenticationBean;
import com.dao.RegistrationBean;
import com.dao.UserDAO;
import com.dao.UsersEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.ValidationException;

/**
 * Created by nikita on 05.01.2017.
 */

@Controller
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);
    UserDAO userDAO;

    @Inject
    public AuthenticationController(UserDAO userDAO){
        if (userDAO == null){
            LOGGER.error("userDAO is null");
            throw new ValidationException("UserDAO cannot be null");
        }
        this.userDAO = userDAO;
    }
    //starting page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(){
        LOGGER.info("starting page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginUser", new AuthenticationBean());
        modelAndView.addObject("registrationUser", new RegistrationBean());
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("loginUser")AuthenticationBean user){
        ModelAndView modelAndView = new ModelAndView();
        String fail = "Email or password is incorrect";
        boolean checkUser = userDAO.checkUser(user.getEmail(),user.getPassword());
        LOGGER.info("Check passed");
        if (checkUser){
            LOGGER.info("Set secondPage in setViewName");
            modelAndView.addObject("loginUser");
            modelAndView.setViewName("secondPage");
        }else {
            LOGGER.info("Set index in setViewName");
            modelAndView.addObject("fail", fail);
            modelAndView.setViewName("index");

        }
        return modelAndView;
    }
}
