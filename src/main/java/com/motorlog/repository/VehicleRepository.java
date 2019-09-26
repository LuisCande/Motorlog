package com.motorlog.repository;

import com.motorlog.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    //Retrieves a vehicle for a certain license plate
    @Query("select v from Vehicle v where v.licensePlate=?1")
    Vehicle vehicleByLicensePlate(String licensePlate);

    //Returns all the filtered vehicles.
    @Query("select v from Vehicle v where v.licensePlate like concat('%', ?1, '%')" +
            " or v.brand like concat('%', ?1, '%')" +
            " or v.model like concat('%', ?1, '%')")
    Collection<Vehicle> getFilteredVehicles(String search);
}