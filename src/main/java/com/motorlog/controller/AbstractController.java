package com.motorlog.controller;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbstractController {

    // Panic handler ----------------------------------------------------------

    @ExceptionHandler(Throwable.class)
    public ModelAndView panic(final Throwable oops) {
        ModelAndView result;

        result = new ModelAndView("misc/panic");
        result.addObject("name", ClassUtils.getShortName(oops.getClass()));
        result.addObject("exception", oops.getMessage());
        result.addObject("stackTrace", oops.getStackTrace());

        return result;
    }

}
