package com.controller;

import com.dao.AuthenticationBean;
import com.dao.RegistrationBean;
import com.dao.UserDAO;
import com.dao.UsersEntity;
import org.apache.catalina.connector.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Map;

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

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public ModelAndView fail(){
        LOGGER.info("fail page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fail");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute ("loginUser") AuthenticationBean loginUser,HttpServletRequest request,RedirectAttributes redirectAttributes){
        LOGGER.info("In the login method");
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        boolean checkUser = userDAO.checkUser(loginUser.getEmail(),loginUser.getPassword());
        if (checkUser){
            LOGGER.info("Check passed");
            modelAndView.addObject("loginUser");
            return new ModelAndView("redirect:api","loginUser", loginUser.toString());
        }else {
            LOGGER.info("Set index in setViewName");
            //session.setAttribute("fail", "Email or password is incorrect");
            redirectAttributes.addFlashAttribute("fail","Email or password is incorrect");
            return new ModelAndView("redirect:/fail");
        }
    }
}
