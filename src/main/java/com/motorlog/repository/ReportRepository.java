package com.motorlog.repository;

import com.motorlog.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    //Returns all the reports of a certain garage
    @Query("select r from Report r where r.garage.id = ?1")
    Collection<Report> getReportsByGarage(int varId);

}