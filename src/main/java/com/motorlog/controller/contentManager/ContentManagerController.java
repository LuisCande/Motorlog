package com.motorlog.controller.contentManager;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Actor;
import com.motorlog.entity.Administrator;
import com.motorlog.entity.ContentManager;
import com.motorlog.entity.Garage;
import com.motorlog.service.ActorService;
import com.motorlog.service.ContentManagerService;
import com.motorlog.service.GarageService;
import com.motorlog.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/contentManager")
public class ContentManagerController extends AbstractController {

    //Services
    @Autowired
    private GarageService garageService;

    @Autowired
    private ContentManagerService contentManagerService;

    @Autowired
    private ActorService actorService;

    //Creation

    @RequestMapping(value = "/createGarage", method = RequestMethod.GET)
    public ModelAndView createGarage() {
        final ModelAndView result;
        Garage garage;

        garage = this.garageService.create();
        result = this.createEditModelAndView(garage);

        return result;
    }

    @RequestMapping(value = "/createContentManager", method = RequestMethod.GET)
    public ModelAndView createContentManager() {
        final ModelAndView result;
        ContentManager contentManager;

        contentManager = this.contentManagerService.create();
        result = this.createEditModelAndView(contentManager);

        return result;
    }

    @RequestMapping(value = "/editGarage", method = RequestMethod.POST, params = "save")
    public ModelAndView saveGarage(@Valid final Garage garage, final BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors())
            result = this.createEditModelAndView(garage);
        else
            try {
                this.actorService.hashPassword(garage);
                this.garageService.save(garage);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(garage, "No se ha podido crear la cuenta");
            }
        return result;
    }

    @RequestMapping(value = "/editContentManager", method = RequestMethod.POST, params = "save")
    public ModelAndView saveContentManager(@Valid final ContentManager contentManager, final BindingResult binding) {
        ModelAndView result;

        if (binding.hasErrors())
            result = this.createEditModelAndView(contentManager);
        else
            try {
                this.actorService.hashPassword(contentManager);
                this.contentManagerService.save(contentManager);
                result = new ModelAndView("redirect:/");
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(contentManager, "No se ha podido crear la cuenta");
            }
        return result;
    }

    protected ModelAndView createEditModelAndView(final Garage garage) {
        ModelAndView result;

        result = this.createEditModelAndView(garage, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final Garage garage, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("garage/edit");
        result.addObject("garage", garage);
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/contentManager/editGarage");

        return result;

    }

    protected ModelAndView createEditModelAndView(final ContentManager contentManager) {
        ModelAndView result;

        result = this.createEditModelAndView(contentManager, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(final ContentManager contentManager, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("contentManager/edit");
        result.addObject("contentManager", contentManager);
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/contentManager/editContentManager");

        return result;

    }
}
