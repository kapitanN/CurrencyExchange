package com.controller;

import com.dao.AuthenticationBean;
import com.dao.RegistrationBean;
import com.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.ValidationException;

/**
 * Created by nikita on 03.01.2017.
 */

@Controller(value = "/")
@SessionAttributes("registrationUser")
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
    public String registration(@ModelAttribute("registrationUser") RegistrationBean registrationUser, RedirectAttributes redirectAttributes){
        LOGGER.info("registration method started");
        boolean checkUser = userDAO.checkUser(registrationUser.getEmail());
        if (!checkUser){
            userDAO.addUser(registrationUser);
            return "redirect:api";
        }
        else {
            LOGGER.info("Validation fails in registration");
            redirectAttributes.addFlashAttribute("fail","User with this email already exists");
            return "redirect:/";
        }
    }
}
