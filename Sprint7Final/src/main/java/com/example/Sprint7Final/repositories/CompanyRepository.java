package com.example.Sprint7Final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Sprint7Final.entities.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByDeletedFalse();

    Optional<Company> findByIdAndDeletedFalse(Long companyID);

}
