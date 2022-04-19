package com.newworldcompanytracker.repository;

import com.newworldcompanytracker.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyEntityRepository extends JpaRepository<CompanyEntity, String> {

    @Query(value = "SELECT * FROM company_entity t where t.company_name=:companyName ", nativeQuery = true)
    CompanyEntity findCompany(@Param("companyName") String companyName);

}