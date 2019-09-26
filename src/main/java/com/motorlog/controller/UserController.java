package com.motorlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//********************************************************************************************************************//
//* https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp *//
//********************************************************************************************************************//
// We don't define "/login" POST controller, it is provided by Spring Security.

@Controller
public class UserController {
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Tu usuario o tu clave son incorrectas.");
        if (logout != null)
            model.addAttribute("message", "Has salido del sistema satisfactoriamente.");
        return "login";
    }
}