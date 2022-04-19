package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyEntity;
import com.newworldcompanytracker.repository.CompanyEntityRepository;
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
public class IsCompanyExistService implements Predicate<String> {

    private final CompanyEntityRepository companyEntityRepository;

    @Override
    public boolean test(String companyName) {

        final List<CompanyEntity> companyList = companyEntityRepository.findAll();
        final Optional<CompanyEntity> companyEntity = companyList.stream().filter(t -> t.getCompanyName().equals(companyName)).findAny();

        return companyEntity.isPresent();
    }
}
