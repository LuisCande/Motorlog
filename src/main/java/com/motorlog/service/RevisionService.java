package com.motorlog.service;

import com.motorlog.entity.*;
import com.motorlog.repository.RevisionRepository;
import com.motorlog.security.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.*;


@Service
@Transactional
public class RevisionService {

    // Managed service
    @Autowired
    private RevisionRepository revisionRepository;

    // Supporting service

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ActorService actorService;

    // Simple CRUD methods

    public Revision create(int varId) {
        final Revision r = new Revision();
        Vehicle v = this.vehicleService.findOne(varId);
        Garage g = (Garage) this.actorService.findByPrincipal();
        Collection<Boolean> isSubtituted = new ArrayList<>();
        r.setVehicle(v);
        r.setGarage(g);
        r.setEntryDate(new Date(System.currentTimeMillis() - 1));
        Configuration c = this.configurationService.findAll().iterator().next();

        if(r.getVehicle().getType().equals(VehicleType.motorbike))
            for(String s: c.getItemsForMoto())
                isSubtituted.add(false);
        else
            for(String s: c.getItemsForCar())
                isSubtituted.add(false);

        r.setIsSubstituted(isSubtituted);

        return r;
    }

    public Revision findOne(final int id) {
        Revision revision = null;
        Optional<Revision> optionalRevision = this.revisionRepository.findById(id);
        if(optionalRevision.isPresent()){
            revision = optionalRevision.get();
        }
        return revision;
    }

    public List<Revision> findAll() {
        return this.revisionRepository.findAll();
    }

    public Revision save(final Revision r) {
        Assert.notNull(r, "Revision is null");
        Authority a = new Authority();
        a.setAuthority(Authority.GARAGE);

        Authority b = new Authority();
        b.setAuthority(Authority.CONTENTMANAGER);

        if(r.getId()!=0) {
            Revision rbd = this.findOne(r.getId());
            if(rbd != null) {
                Vehicle v = rbd.getVehicle();
                Assert.isTrue(v.getId() == (r.getVehicle().getId()), "Its not same vehicle");
                Assert.isTrue(rbd.getGarage().getId() == r.getGarage().getId(), "Its not same garage");
            }
        }

        Assert.isTrue(this.actorService.findByPrincipal().getId() == r.getGarage().getId() || this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(b),"Its not the same garage than repair garage.");

        if(r.getDepartureDate()!=null)
            Assert.isTrue(r.getDepartureDate().after(r.getEntryDate()),"The departure date must be after entry date");

        if (r.getItemsPrice() == null) r.setItemsPrice(0.00);
        if (r.getLabourPrice() == null) r.setLabourPrice(0.00);
        r.setFinalPrice(r.getItemsPrice() + r.getLabourPrice());

        return this.revisionRepository.save(r);

    }

    public Revision saveAux(final Revision r) {
        return this.revisionRepository.save(r);
    }

    public void delete(final Revision r) {
        Assert.notNull(r, "Revision is null");

        Authority a = new Authority();
        a.setAuthority(Authority.CONTENTMANAGER);

        //Assertion to make sure that the user trying to delete this entity has the correct privilege.
        Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(a), "It can only be deleted by a content manager");

        this.revisionRepository.delete(r);
    }

    //Other methods

    //Returns all the revisions of a certain garage
    public Collection<Revision> getRevisionsByGarage(int varId){
        return this.revisionRepository.getRevisionsByGarage(varId);
    }

    //Returns all the pending revisions of a certain garage
    public Collection<Revision> getPendingRevisionsByGarage(int varId){
        return this.revisionRepository.getPendingRevisionsByGarage(varId);
    }

    //Returns all the closed revisions of a certain garage
    public Collection<Revision> getClosedRevisionsByGarage(int varId){
        return this.revisionRepository.getClosedRevisionsByGarage(varId);
    }

    //Returns all the filtered revisions of a certain garage
    //All fields are nullable and won't affect the filter if not provided
    public Collection<Revision> getFilteredRevisionsByGarage(
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
        return this.revisionRepository.getFilteredRevisionByGarage(varId, search,
                lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
    }

    //Returns all the revisions of a certain vehicle
    public Collection<Revision> getRevisionsByVehicle(int varId){
        return this.revisionRepository.getRevisionsByVehicle(varId);
    }

    //Returns all the pending revisions of a certain vehicle
    public Collection<Revision> getPendingRevisionsByVehicle(int varId){
        return this.revisionRepository.getPendingRevisionsByVehicle(varId);
    }

    //Returns all the closed revisions of a certain vehicle
    public Collection<Revision> getClosedRevisionsByVehicle(int varId){
        return this.revisionRepository.getClosedRevisionsByVehicle(varId);
    }

    //Returns all the pending revisions of a certain garage and vehicle
    public Collection<Revision> revisionsPendingByGarageAndVehicle(int garageId, int vehicleId){
        return this.revisionRepository.revisionsPendingByGarageAndVehicle(garageId, vehicleId);
    }

    //Returns all the filtered revisions of a certain vehicle
    //All fields are nullable and won't affect the filter if not provided
    public Collection<Revision> getFilteredRevisionsByVehicle(
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
        return this.revisionRepository.getFilteredRevisionByVehicle(varId, search,
                lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
    }

    //Other methods

    //The minimum, the maximum and the average of revisions per garage.
    public String[] minMaxAvgRevisionsPerGarage(){
        return this.revisionRepository.minMaxAvgRevisionsPerGarage();
    }

    //The minimum, the maximum and the average of revisions per vehicle.
    public String[] minMaxAvgRevisionsPerVehicle(){
        return this.revisionRepository.minMaxAvgRevisionsPerVehicle();
    }

}