package com.motorlog.controller;

import com.motorlog.entity.Actor;
import com.motorlog.entity.Configuration;
import com.motorlog.security.Authority;
import com.motorlog.service.ActorService;
import com.motorlog.service.ConfigurationService;
import com.motorlog.service.ExportService;
import com.motorlog.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.security.Principal;

@Controller

public class WelcomeController extends AbstractController{

    // https://dashboard.stripe.com/test/plans/
    private final String stripePlan = "monthly";

    @Autowired
    private ActorService actorService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ExportService exportService;

    private ConfigurationService configurationService;

    public WelcomeController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @RequestMapping("/")
    public ModelAndView welcome(Principal user) {
        ModelAndView result = new ModelAndView("welcome");
        Authority a = new Authority();
        a.setAuthority(Authority.GARAGE);
        Configuration configuration = this.configurationService.findAll().iterator().next();
        if(user==null) {
            result = new ModelAndView("welcome");
            result.addObject("configuration", configuration);
        }

        else if(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(a)) {
            if (!subscriptionService.isSubscribed()) {
                result = new ModelAndView("welcome");
                result.addObject("configuration", configuration);
                result.addObject("isSubscribed", subscriptionService.isSubscribed());
                result.addObject("email", getEmail());
            } else
                result = new ModelAndView("redirect:/vehicle/garage/scan");
        }


        return result;
    }

    @RequestMapping("/profile")
    public ModelAndView profile() {
        ModelAndView result;

        result = new ModelAndView("profile");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());
        result.addObject("actor", this.actorService.findByPrincipal());

        return result;
    }


    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ModelAndView subscribe(@RequestParam final String stripeToken) {
        subscriptionService.subscribe(stripeToken, stripePlan);
        return new ModelAndView("redirect:/vehicle/garage/scan");
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public ModelAndView unsubscribe() {
        subscriptionService.unsubscribe();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseEntity<Resource> export() {
        int id = actorService.findByPrincipal().getId();
        String data = exportService.getJson(id, true);
        try {
            File file = File.createTempFile("data", ".json");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.write(data);
            printWriter.close();

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentLength(file.length()).contentType(MediaType.APPLICATION_JSON_UTF8).body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getEmail() {
        Actor actor = actorService.findByPrincipal();
        return actor.getEmail();
    }

    @RequestMapping(value = "/privacy")
    public ModelAndView privacy() {
        ModelAndView result;

        result = new ModelAndView("legislation/privacy");

        return result;
    }

    @RequestMapping(value = "/terms")
    public ModelAndView terms() {
        ModelAndView result;

        result = new ModelAndView("legislation/terms");

        return result;
    }

    @RequestMapping(value = "/about")
    public ModelAndView contact() {
        ModelAndView result;

        result = new ModelAndView("legislation/about");

        return result;
    }
}