package com.motorlog.repository;

import com.motorlog.entity.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.function.DoubleUnaryOperator;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer> {

    //Returns all the revisions of a certain garage
    @Query("select r from Revision r where r.garage.id=?1")
    Collection<Revision> getRevisionsByGarage(int varId);

    //Returns all the pending revisions of a certain garage
    @Query("select r from Revision r where r.garage.id=?1 and r.departureDate is null")
    Collection<Revision> getPendingRevisionsByGarage(int varId);

    //Returns all the closed revisions of a certain garage
    @Query("select r from Revision r where r.garage.id=?1 and r.departureDate is not null")
    Collection<Revision> getClosedRevisionsByGarage(int varId);

    //Returns all the filtered revisions of a certain garage
    @Query("select r from Revision r where r.garage.id = ?1" +
            " and ((?2 = '') or (r.comments like concat('%', ?2, '%')))" +
            " and ((r.entryDate is null) or (r.entryDate between ?3 and ?4))" +
            " and ((r.departureDate is null) or (r.departureDate between ?5 and ?6))" +
            " order by r.departureDate desc nulls first")
    Collection<Revision> getFilteredRevisionByGarage(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate
    );

    //Returns all the revisions of a certain vehicle
    @Query("select r from Revision r where r.vehicle.id = ?1")
    Collection<Revision> getRevisionsByVehicle(int varId);

    //Returns all the pending revisions of a certain vehicle
    @Query("select r from Revision r where r.vehicle.id = ?1 and r.departureDate is null")
    Collection<Revision> getPendingRevisionsByVehicle(int varId);

    //Returns all the closed revisions of a certain vehicle
    @Query("select r from Revision r where r.vehicle.id = ?1 and r.departureDate is not null")
    Collection<Revision> getClosedRevisionsByVehicle(int varId);

    //Returns all the filtered revisions of a certain vehicle
    @Query("select r from Revision r where r.vehicle.id = ?1" +
            " and ((?2 = '') or (r.comments like concat('%', ?2, '%')))" +
            " and ((r.entryDate is null) or (r.entryDate between ?3 and ?4))" +
            " and ((r.departureDate is null) or (r.departureDate between ?5 and ?6))" +
            " order by r.departureDate desc nulls first")
    Collection<Revision> getFilteredRevisionByVehicle(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate
    );

    //The minimum, the maximum and the average of revisions per garage.
    @Query("select min((select count(r) from Revision r where r.garage.id=g.id)), " +
            "max((select count(r) from Revision r where r.garage.id=g.id)), " +
            "avg((select count(r) from Revision r where r.garage.id=g.id))" +
            "from Garage g")
    String[] minMaxAvgRevisionsPerGarage();

    //The minimum, the maximum and the average of revisions per vehicle.
    @Query("select min((select count(r) from Revision r where r.vehicle.id=v.id)), " +
            "max((select count(r) from Revision r where r.vehicle.id=v.id)), " +
            "avg((select count(r) from Revision r where r.vehicle.id=v.id)) " +
            "from Vehicle v")
    String[] minMaxAvgRevisionsPerVehicle();

    //Returns all the pending revisions of a certain garage and vehicle
    @Query("select r from Revision r where r.departureDate is null and r.garage.id=?1 and r.vehicle.id=?2")
    Collection<Revision> revisionsPendingByGarageAndVehicle(int garageId, int vehicleId);
}