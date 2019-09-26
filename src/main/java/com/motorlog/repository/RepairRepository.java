package com.motorlog.repository;

import com.motorlog.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {

    //Returns all the repairs of a certain garage
    @Query("select r from Repair r where r.garage.id = ?1")
    Collection<Repair> getRepairsByGarage(int varId);

    //Returns all the pending repairs of a certain garage
    @Query("select r from Repair r where r.garage.id = ?1 and r.departureDate is null")
    Collection<Repair> getPendingRepairsByGarage(int varId);

    //Returns all the closed repairs of a certain garage
    @Query("select r from Repair r where r.garage.id = ?1 and r.departureDate is not null")
    Collection<Repair> getClosedRepairsByGarage(int varId);

    //Returns all the filtered repairs of a certain garage
    @Query("select r from Repair r where r.garage.id = ?1" +
            " and ((?2 = '') or (r.cause like concat('%', ?2, '%'))" +
            " or (r.actions like concat('%', ?2, '%')))" +
            " and ((r.entryDate is null) or (r.entryDate between ?3 and ?4))" +
            " and ((r.departureDate is null) or (r.departureDate between ?5 and ?6))" +
            " order by r.departureDate desc nulls first")
    Collection<Repair> getFilteredRepairsByGarage(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate
    );

    //Returns all the repairs of a certain vehicle
    @Query("select r from Repair r where r.vehicle.id = ?1")
    Collection<Repair> getRepairsByVehicle(int varId);

    //Returns all the pending repairs of a certain vehicle
    @Query("select r from Repair r where r.vehicle.id = ?1 and r.departureDate is null")
    Collection<Repair> getPendingRepairsByVehicle(int varId);

    //Returns all the closed repairs of a certain vehicle
    @Query("select r from Repair r where r.vehicle.id = ?1 and r.departureDate is not null")
    Collection<Repair> getClosedRepairsByVehicle(int varId);

    //Returns all the filtered repairs of a certain vehicle
    @Query("select r from Repair r where r.vehicle.id = ?1" +
            " and ((?2 = '') or (r.cause like concat('%', ?2, '%'))" +
            " or (r.actions like concat('%', ?2, '%')))" +
            " and ((r.entryDate is null) or (r.entryDate between ?3 and ?4))" +
            " and ((r.departureDate is null) or (r.departureDate between ?5 and ?6))" +
            " order by r.departureDate desc nulls first")
    Collection<Repair> getFilteredRepairsByVehicle(
            int varId, String search,
            Date lowerEntryDate, Date upperEntryDate,
            Date lowerDepartureDate, Date upperDepartureDate
    );

    //The minimum, the maximum and the average of repairs per garage.
    @Query("select min((select count(r) from Repair r where r.garage.id=g.id)), " +
            "max((select count(r) from Repair r where r.garage.id=g.id)), " +
            "avg((select count(r) from Repair r where r.garage.id=g.id))" +
            "from Garage g")
    String[] minMaxAvgRepairsPerGarage();

    //The minimum, the maximum and the average of repairs per vehicle.
    @Query("select min((select count(r) from Repair r where r.vehicle.id=v.id)), " +
            "max((select count(r) from Repair r where r.vehicle.id=v.id)), " +
            "avg((select count(r) from Repair r where r.vehicle.id=v.id)) " +
            "from Vehicle v")
    String[] minMaxAvgRepairsPerVehicle();

    //Returns all the pending repairs of a certain garage and vehicle
    @Query("select r from Repair r where r.departureDate is null and r.garage.id=?1 and r.vehicle.id=?2")
    Collection<Repair> repairsPendingByGarageAndVehicle(int garageId, int vehicleId);
}