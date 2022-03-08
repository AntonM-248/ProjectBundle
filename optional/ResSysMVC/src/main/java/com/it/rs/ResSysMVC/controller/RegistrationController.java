package com.it.rs.ResSysMVC.controller;

import com.it.rs.ResSysMVC.exception.UserIdAlreadyPresentException;
import com.it.rs.ResSysMVC.model.User;
import com.it.rs.ResSysMVC.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private Environment environment;

    private String command = "command";
    private String register = "register";

    @GetMapping(value = "/register")
    public ModelAndView register(Model model){
        return new ModelAndView(register, command, new User());
    }

    @PostMapping(value = "registerUser")
    public ModelAndView addCustomer(@Valid @ModelAttribute("command") User user, BindingResult result, ModelMap model){
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView = new ModelAndView(register, command, user);
        } else {
            try {
                registrationService.registerUser(user);
                modelAndView = new ModelAndView(register, command, user);
                modelAndView.addObject("successMessage", environment.getProperty("RegistrationController.SUCCESSFUL_REGISTRATION"));
            } catch (UserIdAlreadyPresentException e) {
                if(e.getMessage().contains("RegistrationService")) {
                    modelAndView = new ModelAndView(register);
                    modelAndView.addObject(command, user);
                    modelAndView.addObject("message", environment.getProperty(e.getMessage()));
                }
            }
        }
        return modelAndView;
    }

}
