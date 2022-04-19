package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyAdminEntity;
import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import com.newworldcompanytracker.model.request_response.CompanyMemberRemoveGearRequest;
import com.newworldcompanytracker.model.request_response.CompanyMembersGearResponse;
import com.newworldcompanytracker.repository.CompanyAdminEntityRepository;
import com.newworldcompanytracker.repository.CompanyMemberGearEntityRepository;
import com.newworldcompanytracker.service.task.SimpleTask;
import com.newworldcompanytracker.type.Rank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyMemberRemoveGearService implements SimpleTask<CompanyMemberRemoveGearRequest, CompanyMembersGearResponse> {

    private final CompanyMemberGearEntityRepository companyMemberGearEntityRepository;

    private final CompanyAdminEntityRepository companyAdminEntityRepository;

    @Override
    public CompanyMembersGearResponse apply(CompanyMemberRemoveGearRequest request) {

        final List<CompanyAdminEntity> adminList = companyAdminEntityRepository.findAll();
        final Optional<CompanyAdminEntity> admin = adminList
                .stream()
                .filter(t -> t.getCompanyName().equals(request.getCompanyName()))
                .filter(t -> t.getUsername().equals(request.getAdminName())).findAny();

        if (admin.isPresent()) {

            final CompanyAdminEntity entity = admin.get();

            if (Rank.LEADER.equals(entity.getUserRank()) || Rank.CONSUL.equals(entity.getUserRank()) || Rank.OFFICER.equals(entity.getUserRank())) {

                final CompanyMemberGearEntity gearEntity = companyMemberGearEntityRepository.removeGears(request.getCompanyName(), request.getUsername(), request.getBuildNumber());
                companyMemberGearEntityRepository.delete(gearEntity);

                return CompanyMembersGearResponse.builder().status(true).build();
            }

        }

        return null;
    }
}
