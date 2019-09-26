package com.motorlog.controller;

import com.motorlog.entity.Actor;
import com.motorlog.entity.Repair;
import com.motorlog.entity.Vehicle;
import com.motorlog.service.ActorService;
import com.motorlog.service.RepairService;
import com.motorlog.service.SubscriptionService;
import com.motorlog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("actor")
public class ActorController extends AbstractController {

    //Services

    @Autowired
    private ActorService actorService;

    //List

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        final ModelAndView result;
        final Collection<Actor> actors = this.actorService.findAll();;
        Actor principal = actorService.findByPrincipal();
        actors.remove(principal);
        result = new ModelAndView("actor/list");
        result.addObject("actors", actors);
        result.addObject("requestURI", "/actor/list");

        return result;
    }

}