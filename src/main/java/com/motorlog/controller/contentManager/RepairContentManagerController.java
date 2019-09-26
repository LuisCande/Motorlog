package com.motorlog.controller.contentManager;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Repair;
import com.motorlog.entity.Vehicle;
import com.motorlog.service.ActorService;
import com.motorlog.service.RepairService;
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
@RequestMapping("repair/contentManager")
public class RepairContentManagerController extends AbstractController {

    //Services

    @Autowired
    private RepairService repairService;

    @Autowired
    private ActorService		actorService;

    @Autowired
    private VehicleService vehicleService;

    //Delete
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam final int varId) {
        final ModelAndView result;
        final Collection<Vehicle> vehicles;

        Repair repair = this.repairService.findOne(varId);

        if(repair==null)
            return new ModelAndView("redirect:/");

        this.repairService.delete(repair);

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

        Repair repair = this.repairService.findOne(varId);

        if(repair==null)
            return new ModelAndView("redirect:/");

        repair.setDepartureDate(new Date(System.currentTimeMillis()-1));

        Repair saved = this.repairService.save(repair);

        Collection<Repair> repairs = this.repairService.getRepairsByVehicle(saved.getVehicle().getId());

        result = new ModelAndView("repair/list");
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("vehicle",saved.getVehicle());
        result.addObject("repairs", repairs);
        result.addObject("requestURI", "/repair/garage/listByVehicle?varId=" + saved.getVehicle().getId());


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

        return result;
    }

    @RequestMapping(value = "/listByVehicle", method = RequestMethod.GET)
    public ModelAndView listByVehicle(@RequestParam int varId) {
        final ModelAndView result;
        final Collection<Repair> repairs;
        final Vehicle vehicle= this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        repairs = this.repairService.getRepairsByVehicle(varId);

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("vehicle", vehicle);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("requestURI", "/repair/garage/listByVehicle");

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

        int garageId = actorService.findByPrincipal().getId();
        repairs = this.repairService.getFilteredRepairsByGarage(garageId, search, parseDate(lowerEntryDate),
                parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("requestURI", "/repair/garage/filterByGarage");

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

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        repairs = this.repairService.getFilteredRepairsByVehicle(varId, search, parseDate(lowerEntryDate),
                parseDate(upperEntryDate), parseDate(lowerDepartureDate), parseDate(upperDepartureDate));

        result = new ModelAndView("repair/list");
        result.addObject("repairs", repairs);
        result.addObject("vehicle", vehicle);
        result.addObject("search", search);
        result.addObject("lowerEntryDate", lowerEntryDate);
        result.addObject("upperEntryDate", upperEntryDate);
        result.addObject("lowerDepartureDate", lowerDepartureDate);
        result.addObject("upperDepartureDate", upperDepartureDate);
        result.addObject("requestURI", "/repair/garage/filterByVehicle");

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
        result.addObject("repair", repair);
        result.addObject("actorId",this.actorService.findByPrincipal().getId());
        result.addObject("requestURI", "/repair/garage/display");

        return result;
    }

}