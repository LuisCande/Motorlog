package com.motorlog.controller;

import com.motorlog.entity.Garage;
import com.motorlog.service.ActorService;
import com.motorlog.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("garage")
public class GarageController extends AbstractController {

    //Services

    @Autowired
    private GarageService garageService;

    @Autowired
    private ActorService actorService;


    //Delete
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete() {

        try{
            this.garageService.delete((Garage) this.actorService.findByPrincipal());
            return new ModelAndView("redirect:/logout");
        }catch (final Throwable oops) {
            return new ModelAndView("redirect:/");
        }

    }

}
