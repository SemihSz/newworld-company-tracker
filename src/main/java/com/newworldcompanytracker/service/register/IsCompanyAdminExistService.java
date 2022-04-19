package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyAdminEntity;
import com.newworldcompanytracker.repository.CompanyAdminEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IsCompanyAdminExistService implements Predicate<String> {

    private final CompanyAdminEntityRepository companyAdminEntityRepository;

    @Override
    public boolean test(String username) {

        final List<CompanyAdminEntity> companyList = companyAdminEntityRepository.findAll();
        final Optional<CompanyAdminEntity> adminEntity = companyList.stream().filter(t -> t.getUsername().equals(username)).findAny();

        return adminEntity.isPresent();
    }
}
