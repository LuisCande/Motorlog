package com.motorlog.service;

import com.motorlog.entity.Garage;
import com.motorlog.security.Authority;
import com.motorlog.entity.Vehicle;

import com.motorlog.entity.Repair;
import com.motorlog.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class RepairService {

    //Managed repository
    @Autowired
    private RepairRepository repairRepository;

    //Supporting services

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ActorService actorService;

    //CRUD Methods

    public Repair create(final int varId) {
        final Repair r = new Repair();

        Garage g = (Garage) this.actorService.findByPrincipal();
        Vehicle v = this.vehicleService.findOne(varId);
        r.setEntryDate(new Date(System.currentTimeMillis()-1));
        r.setGarage(g);
        r.setVehicle(v);
        return r;
    }

    public Repair findOne(final int id) {
        Repair repair = null;
        Optional<Repair> optionalRepair = this.repairRepository.findById(id);
        if(optionalRepair.isPresent()){
            repair = optionalRepair.get();
        }
        return repair;
    }

    public Collection<Repair> findAll() {
        return repairRepository.findAll();
    }

    public Repair save(final Repair r) {
        Assert.notNull(r, "Repair is null.");

        Authority a = new Authority();
        a.setAuthority(Authority.GARAGE);

        Authority b = new Authority();
        b.setAuthority(Authority.CONTENTMANAGER);

        if(r.getId()!=0) {
            Repair rbd = this.findOne(r.getId());
            if (rbd != null) {
                Vehicle vehicle = rbd.getVehicle();

                //Assertion the repair vehicle given is the same that the stored database repair vehicle
                Assert.isTrue(vehicle.getId() == r.getVehicle().getId(), "Its not the same vehicle.");
                Assert.isTrue(rbd.getGarage().getId() == r.getGarage().getId(), "Its not the same actor.");
            }
        }

        //Assertion to make sure the user who is saving repair has the correct privilege
        Assert.isTrue(this.actorService.findByPrincipal().getId() == r.getGarage().getId() || this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(b),"Its not the same garage than repair garage.");

        //Assertion the departure date must be after entry date
        if(r.getDepartureDate()!= null){
            Assert.isTrue(r.getDepartureDate().after(r.getEntryDate()),"The departure date must be after entry date");
        }

        if (r.getItemsPrice() == null) r.setItemsPrice(0.0);
        if (r.getLabourPrice() == null) r.setLabourPrice(0.0);
        r.setFinalPrice(r.getItemsPrice() + r.getLabourPrice());

        return repairRepository.save(r);
    }

    public Repair saveAux(final Repair r) {
        return this.repairRepository.save(r);
    }

    public void delete(final Repair r) {
        Assert.notNull(r, "Repair is null.");

        Authority a = new Authority();
        a.setAuthority(Authority.CONTENTMANAGER);

        //Assertion to make sure that the user trying to delete this entity has the correct privilege.
        Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(a), "It can only be deleted by a content manager");

        repairRepository.delete(r);
    }

    //Other methods

    //Returns all the repairs of a certain garage
    public Collection<Repair> getRepairsByGarage(int varId){
        return this.repairRepository.getRepairsByGarage(varId);
    }

    //Returns all the pending repairs of a certain garage
    public Collection<Repair> getPendingRepairsByGarage(int varId){
        return this.repairRepository.getPendingRepairsByGarage(varId);
    }

    //Returns all the closed repairs of a certain garage
    public Collection<Repair> getClosedRepairsByGarage(int varId){
        return this.repairRepository.getClosedRepairsByGarage(varId);
    }

    //Returns all the filtered repairs of a certain garage
    //All fields are nullable and won't affect the filter if not provided
    public Collection<Repair> getFilteredRepairsByGarage(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate){
        if (search == null) search = "";
        if (lowerEntryDate == null)
            lowerEntryDate = Date.from(Instant.EPOCH);
        if (upperEntryDate == null)
            upperEntryDate = Date.from(Instant.now());
        if (lowerDepartureDate == null)
            lowerDepartureDate = Date.from(Instant.EPOCH);
        if (upperDepartureDate == null)
            upperDepartureDate = Date.from(Instant.now());
        return this.repairRepository.getFilteredRepairsByGarage(varId, search,
                lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
    }

    //Returns all the repairs of a certain vehicle
    public Collection<Repair> getRepairsByVehicle(int varId){
        return this.repairRepository.getRepairsByVehicle(varId);
    }

    //Returns all the pending repairs of a certain vehicle
    public Collection<Repair> getPendingRepairsByVehicle(int varId){
        return this.repairRepository.getPendingRepairsByVehicle(varId);
    }

    //Returns all the closed repairs of a certain vehicle
    public Collection<Repair> getClosedRepairsByVehicle(int varId){
        return this.repairRepository.getClosedRepairsByVehicle(varId);
    }

    //Returns all the pending revisions of a certain garage and vehicle
    public Collection<Repair> repairsPendingByGarageAndVehicle(int garageId, int vehicleId){
        return this.repairRepository.repairsPendingByGarageAndVehicle(garageId, vehicleId);
    }

    //Returns all the filtered repairs of a certain vehicle
    //All fields are nullable and won't affect the filter if not provided
    public Collection<Repair> getFilteredRepairsByVehicle(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate){
        if (search == null) search = "";
        if (lowerEntryDate == null)
            lowerEntryDate = Date.from(Instant.EPOCH);
        if (upperEntryDate == null)
            upperEntryDate = Date.from(Instant.now());
        if (lowerDepartureDate == null)
            lowerDepartureDate = Date.from(Instant.EPOCH);
        if (upperDepartureDate == null)
            upperDepartureDate = Date.from(Instant.now());
        return this.repairRepository.getFilteredRepairsByVehicle(varId, search,
                lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
    }

    //Other methods

    //The minimum, the maximum and the average of repairs per garage.
    public String[] minMaxAvgRepairsPerGarage(){
        return this.repairRepository.minMaxAvgRepairsPerGarage();
    }

    //The minimum, the maximum and the average of repairs per vehicle.
    public String[] minMaxAvgRepairsPerVehicle(){
        return this.repairRepository.minMaxAvgRepairsPerVehicle();
    }
}
