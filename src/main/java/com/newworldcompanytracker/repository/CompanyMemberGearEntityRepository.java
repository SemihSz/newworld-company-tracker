package com.newworldcompanytracker.repository;

import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyMemberGearEntityRepository extends JpaRepository<CompanyMemberGearEntity, String> {

    @Query(value = "SELECT * FROM company_member_gear_entity t where t.company_name=:companyName and t.username=:username and t.build_number=:buildNumber", nativeQuery = true)
    CompanyMemberGearEntity removeGears(@Param("companyName") String companyName, @Param("username") String username, @Param("buildNumber") Integer buildNumber);

    @Query(value = "SELECT * FROM company_member_gear_entity t where t.company_name=:companyName", nativeQuery = true)
    List<CompanyMemberGearEntity> allCompanyMemberGears(@Param("companyName") String companyName);

}