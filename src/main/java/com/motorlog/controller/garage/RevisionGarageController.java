package com.motorlog.controller.garage;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.*;
import com.motorlog.service.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.engine.spi.CollectionEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.Assert;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("revision/garage")
public class RevisionGarageController extends AbstractController {

    //Services

    @Autowired
    private RevisionService	revisionService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private SubscriptionService subscriptionService;

    //Creation

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam final int varId) {
        final ModelAndView result;
        Revision revision;

        if(this.vehicleService.findOne(varId)==null)
            return new ModelAndView("redirect:/");

        revision = this.revisionService.create(varId);
        result = this.createEditModelAndView(revision);

        return result;
    }

    //Close

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public ModelAndView close(@RequestParam final int varId) {
        final ModelAndView result;
        Boolean canCreate = true;

        Revision revision = this.revisionService.findOne(varId);

        if(revision==null || revision.getGarage().getId() != this.actorService.findByPrincipal().getId())
            return new ModelAndView("redirect:/");

        revision.setDepartureDate(new Date(System.currentTimeMillis()-1));

        Revision saved = this.revisionService.save(revision);

        Collection<Revision> revisions = this.revisionService.getRevisionsByVehicle(saved.getVehicle().getId());

        if(this.revisionService.revisionsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), varId).size()>0)
            canCreate=false;

        result = new ModelAndView("revision/list");

        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle", saved.getVehicle());
        result.addObject("revisions", revisions);
        result.addObject("canCreate", canCreate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+saved.getVehicle().getId());
        result.addObject("requestURI", "/revision/garage/listByVehicle");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int varId) {
        final ModelAndView result;
        final Revision revision = this.revisionService.findOne(varId);

        if(revision==null || revision.getGarage().getId() != this.actorService.findByPrincipal().getId())
            return new ModelAndView("redirect:/");

        result = this.createEditModelAndView(revision);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Revision revision, final BindingResult binding, @RequestParam Integer km,@RequestParam String fullName, @RequestParam String phoneNumber, @RequestParam String address, @RequestParam String name0, @RequestParam String name1, @RequestParam String name2, @RequestParam String name3, @RequestParam String name4, @RequestParam String name5,
                             @RequestParam String name6, @RequestParam String name7, @RequestParam String name8, @RequestParam String name9, @RequestParam String name10,
                             @RequestParam String name11, @RequestParam String name12, @RequestParam String name13, @RequestParam String name14, @RequestParam String name15,
                             @RequestParam String name16, @RequestParam String name17, @RequestParam String name18, @RequestParam String name19, @RequestParam String name20) {
        ModelAndView result = new ModelAndView();
        Revision saved;

        String personalInf = fullName + ";;" + phoneNumber + ";;" + address;

        revision.setPersonalInfo(personalInf);

        Collection<Boolean> isSubstituted = new ArrayList<>() ;

        if(name0.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name1.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name2.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name3.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name4.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name5.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name6.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name7.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name8.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name9.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name10.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name11.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name12.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name13.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name14.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name15.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name16.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name17.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name18.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name19.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);
        if(name20.equals("true")) isSubstituted.add(true);
        else isSubstituted.add(false);

        if (binding.hasErrors()) {
            revision.setIsSubstituted(isSubstituted);
            result = this.createEditModelAndView(revision);
        }else
            try {
                Vehicle vehicle = revision.getVehicle();
                vehicle.setKm(km);
                this.vehicleService.save(vehicle);
                revision.setVehicle(vehicle);
                revision.setIsSubstituted(isSubstituted);
                saved = this.revisionService.save(revision);
                Collection<Revision> revisions = this.revisionService.getRevisionsByVehicle(saved.getVehicle().getId());
                result.addObject("revisions",revisions);
                result = new ModelAndView("redirect:/revision/garage/listByVehicle?varId=" + saved.getVehicle().getId());
                result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
                result.addObject("requestURI", "/revision/garage/listByVehicle");
                result.addObject("isSubscribed", subscriptionService.isSubscribed());
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(revision, "No se puede realizar la operacion.");
            }
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
        result.addObject("returnLink", "/vehicle/garage/scan");
        result.addObject("requestURI", "/revision/garage/listByGarage");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    @RequestMapping(value = "/listByVehicle", method = RequestMethod.GET)
    public ModelAndView listByVehicle(@RequestParam int varId) {
        final ModelAndView result;
        final Collection<Revision> revisions;
        final Vehicle vehicle= this.vehicleService.findOne(varId);
        Boolean canCreate = true;

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        if(this.revisionService.revisionsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), varId).size()>0)
            canCreate=false;

        revisions = this.revisionService.getRevisionsByVehicle(varId);

        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle", vehicle);
        result.addObject("canCreate", canCreate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
        result.addObject("requestURI", "/revision/garage/listByVehicle");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

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
        Boolean canCreate = true;

        int garageId = actorService.findByPrincipal().getId();
        if(search.equals("") && lowerEntryDate.equals("") && upperEntryDate.equals("") && lowerDepartureDate.equals("") && upperDepartureDate.equals("") && search.equals(""))
            revisions = this.revisionService.getRevisionsByGarage(garageId);
        else {
            revisions = this.revisionService.getFilteredRevisionsByGarage(garageId, search, parseDate(lowerEntryDate),
                    parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));
        }
        if(!revisions.isEmpty()) {
            if (this.revisionService.revisionsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), revisions.iterator().next().getId()).size() > 0)
                canCreate = false;
        }
        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("search", search);
        result.addObject("canCreate", canCreate);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("returnLink", "/vehicle/garage/scan");
        result.addObject("requestURI", "/revision/garage/filterByGarage");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

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
        Boolean canCreate = true;
        final Vehicle vehicle= this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");
        if(search.equals("") && lowerEntryDate.equals("") && upperEntryDate.equals("") && lowerDepartureDate.equals("") && upperDepartureDate.equals("") && search.equals(""))
            revisions = this.revisionService.getRevisionsByVehicle(varId);
        else {
            revisions = this.revisionService.getFilteredRevisionsByVehicle(varId, search, parseDate(lowerEntryDate),
                    parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));
        }
        if(!revisions.isEmpty()) {
            if (this.revisionService.revisionsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), varId).size() > 0)
                canCreate = false;
        }
        result = new ModelAndView("revision/list");
        result.addObject("revisions", revisions);
        result.addObject("vehicle", vehicle);
        result.addObject("canCreate", canCreate);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
        result.addObject("requestURI", "/revision/garage/filterByVehicle");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

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

        if(revision.getPersonalInfo()!=null) {
            String[] parts = revision.getPersonalInfo().split(";;");
            String fullName = parts[0];
            String phoneNumber = parts[1];
            String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }

        result.addObject("revision", revision);
        result.addObject("items", items);
        result.addObject("isSubstituted", isSubstituted);
        result.addObject("returnLink", "/revision/garage/listByGarage");
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("requestURI", "revision/garage/display");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    //DisplayV

    @RequestMapping(value = "/displayV", method = RequestMethod.GET)
    public ModelAndView displayV(@RequestParam int varId) {
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

        if(revision.getPersonalInfo()!=null) {
            String[] parts = revision.getPersonalInfo().split(";;");
            String fullName = parts[0];
            String phoneNumber = parts[1];
            String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }

        result.addObject("revision", revision);
        result.addObject("items", items);
        result.addObject("isSubstituted", isSubstituted);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("returnLink", "/revision/garage/listByVehicle?varId="+revision.getVehicle().getId());
        result.addObject("requestURI", "revision/garage/display");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }


    //Ancillary methods

    protected ModelAndView createEditModelAndView(final Revision revision) {
        ModelAndView result;

        result = this.createEditModelAndView(revision, null);
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    protected ModelAndView createEditModelAndView(final Revision revision, final String messageCode) {
        ModelAndView result;
        Vehicle vehicle = revision.getVehicle();
        Collection<String> items = new ArrayList<>();
        Configuration c = this.configurationService.findAll().iterator().next();

        if(revision.getVehicle().getType().equals(VehicleType.motorbike))
            items = c.getItemsForMoto();
        else
            items = c.getItemsForCar();

        result = new ModelAndView("revision/edit");

        if(revision.getPersonalInfo()!=null) {
            String[] parts = revision.getPersonalInfo().split(";;");
            String fullName = parts[0];
            String phoneNumber = parts[1];
            String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }


        result.addObject("vehicle", vehicle);
        result.addObject("revision", revision);
        result.addObject("items", items);
        result.addObject("km", revision.getVehicle().getKm());
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/revision/garage/edit");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;

    }

}
