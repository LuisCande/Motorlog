package com.motorlog.controller.contentManager;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Revision;
import com.motorlog.entity.Vehicle;
import com.motorlog.entity.VehicleType;
import com.motorlog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

@Controller
@RequestMapping("vehicle/contentManager")
public class VehicleContentManagerController extends AbstractController {

    //Services

    @Autowired
     VehicleService vehicleService;

    //Delete
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam final int varId) {
        final ModelAndView result;
        final Collection<Vehicle> vehicles;

        Vehicle vehicle = this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        this.vehicleService.delete(vehicle);

        vehicles = this.vehicleService.findAll();

        result = new ModelAndView("vehicle/list");
        result.addObject("vehicles", vehicles);
        result.addObject("requestURI", "/vehicle/contentManager/list");

        return result;
    }

    //List
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        final ModelAndView result;
        final Collection<Vehicle> vehicles;

        vehicles = this.vehicleService.findAll();

        result = new ModelAndView("vehicle/list");
        result.addObject("vehicles", vehicles);
        result.addObject("requestURI", "/vehicle/contentManager/list");

        return result;
    }

    //Filter
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView filter(@RequestParam String search) {
        final ModelAndView result;
        final Collection<Vehicle> vehicles;

        vehicles = this.vehicleService.getFilteredVehicles(search);

        result = new ModelAndView("vehicle/list");
        result.addObject("vehicles", vehicles);
        result.addObject("search", search);
        result.addObject("requestURI", "/vehicle/contentManager/list");

        return result;
    }

    //Display

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam final int varId) {
        ModelAndView result;
        Vehicle vehicle;
        Boolean tipoMoto = false;

        vehicle = this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        if(vehicle.getType().equals(VehicleType.motorbike))
            tipoMoto = true;
        else  if(vehicle.getType().equals(VehicleType.car))
            tipoMoto = false;

        result = new ModelAndView("vehicle/display");
        result.addObject("vehicle", vehicle);
        result.addObject("tipoMoto", tipoMoto);

        return result;
    }

}
