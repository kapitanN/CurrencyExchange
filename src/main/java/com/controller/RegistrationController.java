package com.controller;

import com.dao.RegistrationBean;
import com.dao.UserDAO;
import com.dao.UsersEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.ValidationException;

/**
 * Created by nikita on 03.01.2017.
 */

@Controller(value = "/")
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
    UserDAO userDAO;

    @Inject
    public RegistrationController(UserDAO userDAO){
        if (userDAO == null){
            LOGGER.error("userDAO is null");
            throw new ValidationException("UserDAO cannot be null");
        }
        this.userDAO = userDAO;
    }
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("registrationUser")RegistrationBean user){
        ModelAndView modelAndView = new ModelAndView();
        userDAO.addUser(user);
        modelAndView.addObject("registrationUser");
        modelAndView.setViewName("thirdPage");
        return modelAndView;
    }
}
