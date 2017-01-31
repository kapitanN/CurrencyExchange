package com.controller;

import com.dao.AuthenticationBean;
import com.dao.RegistrationBean;
import com.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.ValidationException;

/**
 * Created by nikita on 05.01.2017.
 */

@Controller
@SessionAttributes({"loginUser","registrationUser"})
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
    public String login(@ModelAttribute("loginUser") AuthenticationBean loginUser, RedirectAttributes redirectAttributes, ModelAndView modelAndView){
        LOGGER.info("In the login method");
        boolean checkUser = userDAO.checkUser(loginUser.getEmail(),loginUser.getPassword());
        if (checkUser){
            LOGGER.info("Passed checks");
            modelAndView.addObject("loginUser",loginUser);
            return "redirect:api";
        }else {
            LOGGER.info("Validation fails in authentication");
            redirectAttributes.addFlashAttribute("fail","Email or password is incorrect");
            return "redirect:/";
        }
    }
}
