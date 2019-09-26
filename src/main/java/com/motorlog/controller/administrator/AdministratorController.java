package com.motorlog.controller.administrator;

import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Repair;
import com.motorlog.service.GarageService;
import com.motorlog.service.RepairService;
import com.motorlog.service.RevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

    //Services

    @Autowired
    private RevisionService revisionService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private GarageService garageService;

    //Dashboard

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView result;

        result = new ModelAndView("administrator/dashboard");

        result.addObject("minMaxAvgRevisionsPerGarage", Arrays.toString(this.revisionService.minMaxAvgRevisionsPerGarage()));
        result.addObject("minMaxAvgRepairsPerGarage", Arrays.toString(this.repairService.minMaxAvgRepairsPerGarage()));
        result.addObject("minMaxAvgRevisionsPerVehicle", Arrays.toString(this.revisionService.minMaxAvgRevisionsPerVehicle()));
        result.addObject("minMaxAvgRepairsPerVehicle", Arrays.toString(this.repairService.minMaxAvgRepairsPerVehicle()));
        result.addObject("top5GaragesInTermsOfRepairs", this.garageService.top5GaragesInTermsOfRepairs());
        result.addObject("top5GaragesInTermsOfRevisions", this.garageService.top5GaragesInTermsOfRevisions());
        result.addObject("requestURI", "administrator/dashboard");

        return result;
    }
}
