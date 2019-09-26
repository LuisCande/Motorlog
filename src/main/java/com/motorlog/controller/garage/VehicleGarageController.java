package com.motorlog.controller.garage;

import com.motorlog.misc.ALPR;
import com.motorlog.controller.AbstractController;
import com.motorlog.entity.Vehicle;
import com.motorlog.entity.VehicleType;
import com.motorlog.service.SubscriptionService;
import com.openalpr.api.invoker.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.motorlog.service.VehicleService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("vehicle/garage")
public class VehicleGarageController extends AbstractController {

    //Services

    @Autowired
     VehicleService vehicleService;

    @Autowired
    private SubscriptionService subscriptionService;

    //Creating

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        Vehicle vehicle = this.vehicleService.create();

        return this.createEditModelAndView(vehicle);
    }

    @RequestMapping(value = "/scanCreate", method = RequestMethod.GET)
    public ModelAndView scanCreate(@RequestParam final String licensePlate, final int type) {

        Vehicle vehicle = this.vehicleService.create();
        vehicle.setLicensePlate(licensePlate);
        if (type == VehicleType.motorbike.getId())
            vehicle.setType(VehicleType.motorbike);
        else if (type == VehicleType.car.getId())
            vehicle.setType(VehicleType.car);

        return this.createEditModelAndView(vehicle, "No hay ningun vehiculo con esa matricula hasta la fecha");
    }

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public ModelAndView options(@RequestParam final int varId) {
        ModelAndView result;

        Vehicle vehicle = this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        result = new ModelAndView("vehicle/options");
        result.addObject("vehicle", vehicle);
        result.addObject("isSubscribed", subscriptionService.isSubscribed());
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
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    //Scan GET

    @RequestMapping(value = "/scan", method = RequestMethod.GET)
    public ModelAndView scan() {
        final ModelAndView result;

        if(!this.subscriptionService.isSubscribed()) {
            result = new ModelAndView("redirect:/");
            return result;
        }

        result = new ModelAndView("/vehicle/scan");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    //Scan POST
    @RequestMapping(value = "/scan", method = RequestMethod.POST, params = "scan")
    public ModelAndView scan(@RequestParam final String licensePlate) {
        ModelAndView result;

        String newLPlate = licensePlate.toUpperCase();
        int type = this.vehicleService.checkLicensePlate(newLPlate);

        if(type == -1) {
            result = this.scanView(newLPlate, "vehicle.licensePlate.error");
            return result;
        }

        final Vehicle vehicle = this.vehicleService.vehicleByLicensePlate(newLPlate);

        if (vehicle==null)
            return this.scanCreate(newLPlate, type);
        else {
            result = new ModelAndView("redirect:/vehicle/garage/options?varId="+vehicle.getId());
            result.addObject("isSubscribed", subscriptionService.isSubscribed());
            return result;
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam final MultipartFile file) {
        ModelAndView result;

        String licensePlate = "";
        try {
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            licensePlate = ALPR.read(base64);
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }

        result = new ModelAndView("/vehicle/scan");
        result.addObject("licensePlate", licensePlate);
        result.addObject("isSubscribed", subscriptionService.isSubscribed());
        return result;
    }



    //Edition

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int varId) {
        final ModelAndView result;
        final Vehicle vehicle = this.vehicleService.findOne(varId);

        if(vehicle==null)
            return new ModelAndView("redirect:/");

        result = this.createEditModelAndView(vehicle);

        return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Vehicle vehicle, final BindingResult binding, @RequestParam String type) {
        ModelAndView result;
        Vehicle saved;

        if(type.equals("motorbike"))
            vehicle.setType(VehicleType.motorbike);
        else if(type.equals("car"))
            vehicle.setType(VehicleType.car);

        if (binding.hasErrors())
            result = this.createEditModelAndView(vehicle);
        else
            try {
                saved = this.vehicleService.save(vehicle);
                result = new ModelAndView("redirect:/vehicle/garage/options?varId=" + saved.getId());
                result.addObject("isSubscribed", subscriptionService.isSubscribed());
            } catch (final Throwable oops) {
                result = this.createEditModelAndView(vehicle, "No se ha podido registrar el vehiculo");
            }

        return result;
    }

    //Ancillary methods

    //ScanView
    protected ModelAndView scanView(final String licensePlate, final String messageCode) {
        ModelAndView result;

        result = new ModelAndView("vehicle/scan");
        result.addObject("licensePlate", licensePlate);
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/vehicle/garage/scan");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    //CreateEdit
    protected ModelAndView createEditModelAndView(final Vehicle vehicle) {
        ModelAndView result;

        result = this.createEditModelAndView(vehicle, null);
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;
    }

    protected ModelAndView createEditModelAndView(final Vehicle vehicle, final String messageCode) {
        ModelAndView result;
        Boolean createBoton;
        if(vehicle.getId()==0){
            createBoton = true;
        }else{
            createBoton = false;
        }
        result = new ModelAndView("vehicle/edit");
        result.addObject("vehicle", vehicle);
        result.addObject("createBoton", createBoton);
        result.addObject("message", messageCode);
        result.addObject("requestURI", "/vehicle/garage/edit");
        result.addObject("isSubscribed", subscriptionService.isSubscribed());

        return result;

    }
}
