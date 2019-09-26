package com.motorlog.service;

import com.motorlog.entity.*;
import com.motorlog.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ReportService {

    // Managed service
    @Autowired
    private ReportRepository reportRepository;



    // Simple CRUD methods


    public Report findOne(final int id) {
        Report report = null;
        Optional<Report> optionalReport = this.reportRepository.findById(id);
        if(optionalReport.isPresent()){
            report = optionalReport.get();
        }
        return report;
    }


    public Report saveAux(final Report r) {
        return this.reportRepository.save(r);
    }


    //Other methods

    //Returns all the revisions of a certain garage
    public Collection<Report> getReportsByGarage(int varId){
        Collection<Report> res = this.reportRepository.getReportsByGarage(varId);
        if(res!=null)
            return res;
        return new ArrayList<>();
    }


}