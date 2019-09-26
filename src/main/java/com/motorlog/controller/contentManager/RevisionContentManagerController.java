package com.motorlog.controller.contentManager;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.*;
import com.motorlog.service.ActorService;
import com.motorlog.service.ConfigurationService;
import com.motorlog.service.RevisionService;
import com.motorlog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Controller
@RequestMapping("revision/contentManager")
public class RevisionContentManagerController extends AbstractController {

    //Services

    @Autowired
    private RevisionService		revisionService;

    @Autowired
    private ActorService		actorService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ConfigurationService configurationService;

    //Delete
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam final int varId) {
        final ModelAndView result;
        final Collection<Vehicle> vehicles;

        Revision revision = this.revisionService.findOne(varId);

        if(revision==null)
            return new ModelAndView("redirect:/");

        this.revisionService.delete(revision);

        vehicles = this.vehicleService.findAll();

        result = new ModelAndView("vehicle/list");
        result.addObject("vehicles", vehicles);
        result.addObject("requestURI", "/vehicle/contentManager/list");

        return result;
    }

    //Close

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public ModelAndView close(@RequestParam final int varId) {
        final ModelAndView result;

        Revision revision = this.revisionService.findOne(varId);

        if(revision==null)
            return new ModelAndView("redirect:/");

        revision.setDepartureDate(new Date(System.currentTimeMillis()-1));

        Revision saved = this.revisionService.save(revision);

        Collection<Revision> revisions = this.revisionService.getRevisionsByVehicle(saved.getVehicle().getId());

        result = new ModelAndView("revision/list");

        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle", saved.getVehicle());
        result.addObject("revisions", revisions);
        result.addObject("requestURI", "/revision/garage/listByVehicle?varId=" + saved.getVehicle().getId());


        return result;
    }


    //List

    @RequestMapping(value = "/listByGarage", method = RequestMethod.GET)
    public ModelAndView listByGarage() {
        final ModelAndView result;
        final Collection<Revision> revisions;

        int garageId = actorService.findByPrincipal().getId();
        revisions = this.revisionService.getRevisionsByGarage(garageId);

        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("requestURI", "/revision/garage/listByGarage");

        return result;
    }

    @RequestMapping(value = "/listByVehicle", method = RequestMethod.GET)
    public ModelAndView listByVehicle(@RequestParam int varId) {
        final ModelAndView result;
        final Collection<Revision> revisions;
        final Vehicle vehicle= this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        revisions = this.revisionService.getRevisionsByVehicle(varId);

        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle", vehicle);

        result.addObject("requestURI", "/revision/garage/listByVehicle");

        return result;
    }

    //Filter

    @RequestMapping(value = "/filterByGarage", method = RequestMethod.GET)
    public ModelAndView filterByGarage(
            @RequestParam String search,
            @RequestParam String lowerEntryDate,
            @RequestParam String upperEntryDate,
            @RequestParam String lowerDepartureDate,
            @RequestParam String upperDepartureDate) {
        final ModelAndView result;
        final Collection<Revision> revisions;

        int garageId = actorService.findByPrincipal().getId();
        revisions = this.revisionService.getFilteredRevisionsByGarage(garageId, search, parseDate(lowerEntryDate),
                parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));

        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("requestURI", "/revision/garage/filterByGarage");

        return result;
    }

    @RequestMapping(value = "/filterByVehicle", method = RequestMethod.GET)
    public ModelAndView filterByVehicle(
            @RequestParam int varId,
            @RequestParam String search,
            @RequestParam String lowerEntryDate,
            @RequestParam String upperEntryDate,
            @RequestParam String lowerDepartureDate,
            @RequestParam String upperDepartureDate) {
        final ModelAndView result;
        final Collection<Revision> revisions;
        final Vehicle vehicle= this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        revisions = this.revisionService.getFilteredRevisionsByVehicle(varId, search, parseDate(lowerEntryDate),
                parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));

        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("vehicle", vehicle);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("requestURI", "/revision/garage/filterByVehicle");

        return result;
    }

    private Date parseDate(String date) {
        if (date == null || date.isEmpty()) return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Display

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display(@RequestParam int varId) {
        final ModelAndView result;
        final Revision revision = this.revisionService.findOne(varId);

        if(revision==null || revision.getId()==0)
            return new ModelAndView("redirect:/");

        Collection<String> items = new ArrayList<>();
        Configuration c = this.configurationService.findAll().iterator().next();
        Collection<Boolean> isSubstituted = revision.getIsSubstituted();

        if(revision.getVehicle().getType().equals(VehicleType.motorbike))
            items = c.getItemsForMoto();
        else
            items = c.getItemsForCar();

        result = new ModelAndView("revision/display");
        result.addObject("revision", revision);
        result.addObject("items", items);
        result.addObject("isSubstituted", isSubstituted);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("requestURI", "revision/garage/display");

        return result;
    }
}
