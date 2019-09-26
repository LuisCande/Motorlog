package com.motorlog.repository;

import com.motorlog.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean
// CRUD refers Create, Read, Update, Delete

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}