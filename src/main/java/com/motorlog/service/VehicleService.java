package com.motorlog.service;


import com.motorlog.entity.Repair;
import com.motorlog.entity.Revision;
import com.motorlog.entity.Vehicle;
import com.motorlog.entity.VehicleType;
import com.motorlog.repository.VehicleRepository;
import com.motorlog.security.Authority;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class VehicleService {

    //Managed repository

    @Autowired
    private VehicleRepository vehicleRepository;

    //Supporting services

    @Autowired
    private ActorService actorService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private RevisionService revisionService;

    //CRUD Methods

    public Vehicle create() {

        Vehicle v = new Vehicle();

        return v;
    }

    public Vehicle findOne(final int id) {
        Vehicle vehicle = null;
        Optional<Vehicle> optV = this.vehicleRepository.findById(id);
        if(optV.isPresent())
            vehicle = optV.get();

        return vehicle;
    }

    public Collection<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(final Vehicle vehicle) {
        Assert.notNull(vehicle, "Item is null.");

        Authority a = new Authority();
        a.setAuthority(Authority.GARAGE);

        //Assertion to make sure that the user modifying this vehicle is a garage. Since we don't have a direct connection between garage and vehicle.
        Assert.isTrue(this.actorService.getPrincipal().getAuthorities().contains(a), "The principal user isn't a garage.");

        //Assertion to make sure that the vehicle type is contained in the enum. May not be needed.
        Assert.isTrue(VehicleType.car == vehicle.getType() || VehicleType.motorbike == vehicle.getType(), "The vehicle type isn't valid");

        //Assertion to make sure that the license plate is a valid one according to the set patterns.
        Assert.isTrue(this.checkLicensePlate(vehicle.getLicensePlate()) > -1);

        //Created to check if the dates were before/after the dates they were supposed to be if we removed the annotations.
       /* //Assertion to make sure that the next revision is either null or future
        if("".equals(vehicle.getNextRevision()))
            Assert.isTrue(vehicle.getNextRevision().after(new Date()));

        //Assertion to make sure that the registration date is either null or past
        if(vehicle.getRegistrationDate()!=null)
            Assert.isTrue(vehicle.getRegistrationDate().before(new Date()));*/

        //Setting the next revision date depending on the associated manual. Check if there is any manual at all or if it already had any revision.
//        if(vehicle.getManual()!=null && vehicle.getNextRevision()==null) {
//            Optional<Date> optionalFirst = vehicle.getManual().getRevisionDates().stream().findFirst();
//            if(optionalFirst.isPresent()) {
//                Date firstDate = optionalFirst.get();
//                vehicle.setNextRevision(firstDate);
//            }
//        }
//        if(vehicle.getManual()!=null && vehicle.getNextRevision()!=null)
//            vehicle.setNextRevision(vehicle.getManual().getRevisionDates().iterator().next());
//
                    
        return this.vehicleRepository.save(vehicle);
    }

    public void delete(final Vehicle vehicle) {
        Assert.notNull(vehicle, "Item is null.");

        Authority contentM = new Authority();
        contentM.setAuthority(Authority.CONTENTMANAGER);

        //Assertion to make sure that the user modifying this vehicle is a content manager.
        Assert.isTrue(this.actorService.getPrincipal().getAuthorities().contains(contentM), "The principal user isn't a content manager.");

        Collection<Revision> revisions = this.revisionService.getRevisionsByVehicle(vehicle.getId());
        Collection<Repair> repairs = this.repairService.getRepairsByVehicle(vehicle.getId());

        for(Revision r : revisions)
            this.revisionService.delete(r);

        for(Repair re : repairs)
            this.repairService.delete(re);

        vehicleRepository.delete(vehicle);
    }

   /* //Tries to correct the format of license plates
    public String correctLicensePlate(String licensePlate){
        if(licensePlate.contains("-") || licensePlate.matches(" ")) {
            String[] plateSplit = licensePlate.split("\\s+");

            licensePlate.replaceAll("-", "");
        }
        return licensePlate;
    }*/

    //Checking the different patterns for license plates.
    public int checkLicensePlate(String licensePlate){
        int res = -1;

        Vehicle vehicle = this.vehicleByLicensePlate(licensePlate);
        if(vehicle == null || vehicle.getType().equals(VehicleType.motorbike)) {
            //Checking the two motorbike possible license plate patterns. (C1111AAA) or (1111AAAA)
            if (licensePlate.matches("C\\d{4}[a-zA-Z]{3}") || licensePlate.matches("\\d{4}[a-zA-Z]{3}"))
                res = VehicleType.motorbike.getId();
        }
        if(vehicle == null || vehicle.getType().equals(VehicleType.car)) {
                //Checking the two possible car license plate patterns. (A1111AA) or (1111AAAA)
            if (licensePlate.matches("[a-zA-Z]\\d{4}[a-zA-Z]{2}") || licensePlate.matches("\\d{4}[a-zA-Z]{3}"))
                res = VehicleType.car.getId();
        }

        return res;
    }

    //Other methods

    //Retrieves a vehicle for a certain license plate
    public Vehicle vehicleByLicensePlate(String licensePlate){
        return this.vehicleRepository.vehicleByLicensePlate(licensePlate);
    }

    //Retrieves all vehicles that match the filter.
    public Collection<Vehicle> getFilteredVehicles(String search) {
        return this.vehicleRepository.getFilteredVehicles(search);
    }
}
