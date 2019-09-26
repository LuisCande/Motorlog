package com.motorlog.controller.garage;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Repair;
import com.motorlog.entity.Vehicle;
import com.motorlog.service.ActorService;
import com.motorlog.service.RepairService;
import com.motorlog.service.SubscriptionService;
import com.motorlog.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("repair/garage")
//TODO solucionar el problema al descomentar AbstractController
public class RepairGarageController extends AbstractController {

    //Services

    @Autowired
    private RepairService repairService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SubscriptionService subscriptionService;

    //Creation

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@RequestParam final int varId) {
        final ModelAndView result;
        Repair repair;
        Collection<Repair> pendingRepairs = this.repairService.getPendingRepairsByVehicle(varId);

        if(this.vehicleService.findOne(varId)==null)
            return new ModelAndView("redirect:/");

        repair = this.repairService.create(varId);
        result = this.createEditModelAndView(repair);


        return result;
    }

    //Close

    @RequestMapping(value = "/close", method = RequestMethod.GET)
    public ModelAndView close(@RequestParam final int varId) {
        final ModelAndView result;
        Boolean canCreate = true;

        Repair repair = this.repairService.findOne(varId);

        if(repair==null || repair.getGarage().getId() != this.actorService.findByPrincipal().getId())
            return new ModelAndView("redirect:/");

        repair.setDepartureDate(new Date(System.currentTimeMillis()-1));

        Repair saved = this.repairService.save(repair);

        Collection<Repair> repairs = this.repairService.getRepairsByVehicle(saved.getVehicle().getId());

        if(this.repairService.repairsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), varId).size()>0)
            canCreate=false;

        result = new ModelAndView("repair/list");
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle",saved.getVehicle());
        result.addObject("repairs", repairs);
        result.addObject("canCreate", canCreate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+saved.getVehicle().getId());
        result.addObject("requestURI", "/repair/garage/listByVehicle");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());


        return result;
    }

    //Edit
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int varId) {
        final ModelAndView result;
        Repair repair;

        repair = this.repairService.findOne(varId);

        if(repair==null || repair.getGarage().getId() != this.actorService.findByPrincipal().getId())
            return new ModelAndView("redirect:/");

        result = this.createEditModelAndView(repair);
        result.addObject("km", repair.getVehicle().getKm());

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Repair repair, final BindingResult binding, @RequestParam String fullName, @RequestParam String phoneNumber, @RequestParam String address, @RequestParam final Integer km) {
        ModelAndView result = new ModelAndView();
        Repair saved;
        Vehicle vehicle = repair.getVehicle();

        String personalInf = fullName + ";;" + phoneNumber + ";;" + address;

        repair.setPersonalInfo(personalInf);

        if (binding.hasErrors())
            result = this.createEditModelAndView(repair);
        else
            try {
                vehicle.setKm(km);
                this.vehicleService.save(vehicle);
                repair.setVehicle(vehicle);
                saved = this.repairService.save(repair);
                Collection<Repair> repairs = this.repairService.getRepairsByVehicle(saved.getVehicle().getId());
                result.addObject("repairs",repairs);
                result = new ModelAndView("redirect:/repair/garage/listByVehicle?varId=" + saved.getVehicle().getId());
                result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
                result.addObject("requestURI", "/repair/garage/listByVehicle");
                result.addObject("isSubscribed", subscriptionService.isSubscribed());
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(repair, "No se ha podido registrar la reparacion");
            }
        result.addObject("vehicle",vehicle);
        return result;
    }

    //List

    @RequestMapping(value = "/listByGarage", method = RequestMethod.GET)
    public ModelAndView listByGarage() {
        final ModelAndView result;
        final Collection<Repair> repairs;

        int garageId = actorService.findByPrincipal().getId();
        repairs = this.repairService.getRepairsByGarage(garageId);

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("requestURI", "/repair/garage/listByGarage");
        result.addObject("returnLink", "/vehicle/garage/scan");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    @RequestMapping(value = "/listByVehicle", method = RequestMethod.GET)
    public ModelAndView listByVehicle(@RequestParam int varId) {
        final ModelAndView result;
        final Collection<Repair> repairs;
        final Vehicle vehicle= this.vehicleService.findOne(varId);
        Boolean canCreate = true;


        if(vehicle==null)
            return new ModelAndView("redirect:/");

        if(this.repairService.repairsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), varId).size()>0)
            canCreate=false;

        repairs = this.repairService.getRepairsByVehicle(varId);

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("vehicle", vehicle);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("canCreate", canCreate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
        result.addObject("requestURI", "/repair/garage/listByVehicle");
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
        final Collection<Repair> repairs;
        Boolean canCreate = true;

        int garageId = actorService.findByPrincipal().getId();
        if(search.equals("") && lowerEntryDate.equals("") && upperEntryDate.equals("") && lowerDepartureDate.equals("") && upperDepartureDate.equals("") && search.equals(""))
            repairs = this.repairService.getRepairsByGarage(garageId);
        else {
            repairs = this.repairService.getFilteredRepairsByGarage(garageId, search, parseDate(lowerEntryDate),
                    parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));
        }
        if(!repairs.isEmpty()) {
            if (this.repairService.repairsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), repairs.iterator().next().getId()).size() > 0)
                canCreate = false;
        }

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("search", search);
        result.addObject("canCreate", canCreate);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("returnLink", "/vehicle/garage/scan");
        result.addObject("requestURI", "/repair/garage/filterByGarage");
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
        final Collection<Repair> repairs;
        final Vehicle vehicle= this.vehicleService.findOne(varId);
        Boolean canCreate = true;

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        if(search.equals("") && lowerEntryDate.equals("") && upperEntryDate.equals("") && lowerDepartureDate.equals("") && upperDepartureDate.equals("") && search.equals(""))
            repairs = this.repairService.getRepairsByVehicle(varId);
        else {
            repairs = this.repairService.getFilteredRepairsByVehicle(varId, search, parseDate(lowerEntryDate),
                    parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));
        }
        if(!repairs.isEmpty()) {
            if (this.repairService.repairsPendingByGarageAndVehicle(this.actorService.findByPrincipal().getId(), repairs.iterator().next().getId()).size() > 0)
                canCreate = false;
        }
        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("canCreate", canCreate);
        result.addObject("vehicle", vehicle);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("returnLink", "/vehicle/garage/options?varId="+vehicle.getId());
        result.addObject("requestURI", "/repair/garage/filterByVehicle");
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
        ModelAndView result;
        Repair repair;

        repair = this.repairService.findOne(varId);

        if(repair==null || repair.getId()==0)
            return new ModelAndView("redirect:/");

        result = new ModelAndView("repair/display");

        if(repair.getPersonalInfo()!=null) {
            String[] parts = repair.getPersonalInfo().split(";;");
            String fullName = parts[0];
            String phoneNumber = parts[1];
            String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }

        result.addObject("repair", repair);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("returnLink", "/repair/garage/listByGarage");
        result.addObject("requestURI", "/repair/garage/display");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    //DisplayV

    @RequestMapping(value = "/displayV", method = RequestMethod.GET)
    public ModelAndView displayV(@RequestParam int varId) {
        ModelAndView result;
        Repair repair;

        repair = this.repairService.findOne(varId);

        if(repair==null || repair.getId()==0)
            return new ModelAndView("redirect:/");

        result = new ModelAndView("repair/display");

        if(repair.getPersonalInfo()!=null) {
            String[] parts = repair.getPersonalInfo().split(";;");
            String fullName = parts[0];
            String phoneNumber = parts[1];
            String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }

        result.addObject("repair", repair);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("returnLink", "/repair/garage/listByVehicle?varId="+repair.getVehicle().getId());
        result.addObject("requestURI", "/repair/garage/display");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }


    //Ancillary methods

    protected ModelAndView createEditModelAndView(final Repair repair) {
        ModelAndView result;

        result = this.createEditModelAndView(repair, null);
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    protected ModelAndView createEditModelAndView(final Repair repair, final String messageCode) {
        ModelAndView result;
        Vehicle vehicle = repair.getVehicle();

            result = new ModelAndView("repair/edit");

            if(repair.getPersonalInfo()!=null) {
                String[] parts = repair.getPersonalInfo().split(";;");
                String fullName = parts[0];
                String phoneNumber = parts[1];
                String address = parts[2];


            result.addObject("fullName", fullName);
            result.addObject("phoneNumber", phoneNumber);
            result.addObject("address", address);
        }


        result.addObject("vehicle",vehicle);
        result.addObject("repair", repair);
        result.addObject("km", repair.getVehicle().getKm());
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/repair/garage/edit");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;

    }

}