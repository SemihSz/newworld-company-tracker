package com.newworldcompanytracker.repository;

import com.newworldcompanytracker.entity.CompanyAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAdminEntityRepository extends JpaRepository<CompanyAdminEntity, String> {
}