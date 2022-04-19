package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyAdminEntity;
import com.newworldcompanytracker.entity.CompanyEntity;
import com.newworldcompanytracker.model.input.CompanyRegisterInput;
import com.newworldcompanytracker.model.request_response.CompanyRegisterResponse;
import com.newworldcompanytracker.repository.CompanyAdminEntityRepository;
import com.newworldcompanytracker.repository.CompanyEntityRepository;
import com.newworldcompanytracker.service.auth.GenerateHashingSHA1Service;
import com.newworldcompanytracker.service.task.SimpleTask;
import com.newworldcompanytracker.service.token.TokenGeneratorService;
import com.newworldcompanytracker.type.Rank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyRegisterService implements SimpleTask<CompanyRegisterInput, CompanyRegisterResponse> {

    private final TokenGeneratorService tokenGeneratorService;

    private final CompanyEntityRepository entityRepository;

    private final GenerateHashingSHA1Service generateHashingSHA1Service;

    private final CompanyAdminEntityRepository companyAdminEntityRepository;

    @Override
    public CompanyRegisterResponse apply(CompanyRegisterInput companyRegisterInput) {

        final String token = tokenGeneratorService.get();
        final LocalDate createdDate = LocalDate.now();
        String hashString = "";

        try {

            hashString = generateHashingSHA1Service.generateHash(token);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage());
        }

        final CompanyEntity companyEntity = CompanyEntity.builder()
                .companyName(companyRegisterInput.getCompanyName())
                .companyLeader(companyRegisterInput.getLeaderName())
                .localDate(createdDate)
                .status("1")
                .membersSize(companyRegisterInput.getMemberSize())
                .token(hashString)
                .server(companyRegisterInput.getServer())
                .build();

        entityRepository.save(companyEntity);

        final CompanyAdminEntity adminEntity = CompanyAdminEntity.builder()
                .companyName(companyRegisterInput.getCompanyName())
                .username(companyRegisterInput.getLeaderName())
                .userRank(Rank.LEADER)
                .server(companyRegisterInput.getServer())
                .build();

        companyAdminEntityRepository.save(adminEntity);

        return CompanyRegisterResponse.builder()
                .createdDate(createdDate)
                .companyName(companyEntity.getCompanyName())
                .authToken(token)
                .leaderName(companyEntity.getCompanyLeader())
                .status("1")
                .server(companyEntity.getServer())
                .build();
    }
}
