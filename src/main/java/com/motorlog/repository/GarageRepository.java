package com.motorlog.repository;

import com.motorlog.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface GarageRepository extends JpaRepository<Garage, Integer> {

    //Top-5 garages in terms of revisions and revisions
    @Query("select g.name from Garage g inner join Revision rv on rv.garage.id=g.id group by g.id order by count(g.id) desc")
    Collection<Garage> top5GaragesInTermsOfRevisions();

    //Top-5 garages in terms of revisions and repairs
    @Query("select g.name from Garage g inner join Repair rp on rp.garage.id=g.id group by g.id order by count(g.id) desc")
    Collection<Garage> top5GaragesInTermsOfRepairs();

   }