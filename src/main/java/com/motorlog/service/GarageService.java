package com.motorlog.service;

import com.motorlog.entity.Garage;
import com.motorlog.entity.Repair;
import com.motorlog.entity.Report;
import com.motorlog.entity.Revision;
import com.motorlog.repository.ActorRepository;
import com.motorlog.repository.GarageRepository;
import com.motorlog.repository.ReportRepository;
import com.motorlog.security.Authority;
import com.motorlog.security.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class GarageService {

    //Managed repository ---------------------------------

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private ActorRepository actorRepository;

    //Supporting services --------------------------------

    @Autowired
    private ActorService		actorService;

    @Autowired
    private RevisionService		revisionService;

    @Autowired
    private RepairService		repairService;

    @Autowired
    private ReportService reportService;

    //Simple CRUD Methods --------------------------------

    public Garage create() {
        final Authority a = new Authority();
        a.setAuthority(Authority.GARAGE);
        final UserAccount account = new UserAccount();
        account.setAuthorities(Arrays.asList(a));

        final Garage garage = new Garage();

        garage.setUserAccount(account);
        garage.getUserAccount().setBanned(false);
        garage.setCustomer(null);

        return garage;
    }

    public Garage findOne(final int id) {
        Garage garage = null;
        Optional<Garage> optionalGarage = this.garageRepository.findById(id);
        if(optionalGarage.isPresent())
            garage = optionalGarage.get();

        return garage;
    }

    public Collection<Garage> findAll() {
        return this.garageRepository.findAll();
    }

    public Garage save(Garage garage){
        Assert.notNull(garage, "Garage is null");
        Garage saved2;

        if(garage.getId() != 0){
            Assert.isTrue(this.actorService.findByPrincipal().getId() == garage.getId(), "Different actor");
            saved2 = this.garageRepository.save(garage);
        }else{
            Garage saved = this.garageRepository.save(garage);
            saved2 = this.garageRepository.save(saved);
        }

        return saved2;
    }

    public void delete(Garage garage){
        Assert.notNull(garage, "Garage is null");
        Assert.isTrue(this.actorService.findByPrincipal().getId() == garage.getId(), "Different actor");

        for(Revision rv: this.revisionService.getRevisionsByGarage(garage.getId())) {
            rv.setGarage(null);
            this.revisionService.saveAux(rv);
        }
        for(Repair rp: this.repairService.getRepairsByGarage(garage.getId())) {
            rp.setGarage(null);
            this.repairService.saveAux(rp);
        }

        for(Report x: this.reportService.getReportsByGarage(garage.getId())){
            x.setGarage(null);
            this.reportService.saveAux(x);
        }

        this.garageRepository.delete(garage);
    }

    //Other methods

    //Top-5 garages in terms of revisions and revisions
    public Collection<Garage> top5GaragesInTermsOfRevisions(){
        Collection<Garage> res = new ArrayList<>(this.garageRepository.top5GaragesInTermsOfRevisions());
        if(res.size()>4){
            return ((ArrayList<Garage>) res).subList(0,5);
        }return res;
    }

    //Top-5 garages in terms of revisions and repairs
    public Collection<Garage> top5GaragesInTermsOfRepairs() {
        Collection<Garage> res = new ArrayList<>(this.garageRepository.top5GaragesInTermsOfRepairs());
        if (res.size() > 4) {
            return ((ArrayList<Garage>) res).subList(0, 5);
        }
        return res;
    }

}

