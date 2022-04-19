package com.newworldcompanytracker.service.auth;

import com.newworldcompanytracker.entity.CompanyEntity;
import com.newworldcompanytracker.exception.AuthenticationException;
import com.newworldcompanytracker.repository.CompanyEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IsAuthenticationTokenService implements BiConsumer<String, String> {

    private final GenerateHashingSHA1Service generateHashingSHA1Service;

    private final CompanyEntityRepository companyEntityRepository;

    @SneakyThrows
    @Override
    public void accept(String companyName, String token) {

        final CompanyEntity companyEntity = companyEntityRepository.findCompany(companyName);

        if (Objects.nonNull(companyEntity)) {

            final boolean matchedToken = generateHashingSHA1Service.validateHash(token, companyEntity.getToken());
            if (matchedToken) {
                log.info("Crypto api is active...");
            }
            else {
                throw new AuthenticationException("New World Company tracker api authentication token wrong");
            }

        } else {
            throw new AuthenticationException("Company not exist");
        }

    }
}
