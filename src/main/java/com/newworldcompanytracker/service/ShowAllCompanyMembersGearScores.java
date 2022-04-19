package com.newworldcompanytracker.service;

import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import com.newworldcompanytracker.model.request_response.CompanyShowAllMemberGears;
import com.newworldcompanytracker.model.request_response.CompanyShowAllMemberGearsRequest;
import com.newworldcompanytracker.repository.CompanyMemberGearEntityRepository;
import com.newworldcompanytracker.service.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShowAllCompanyMembersGearScores implements SimpleTask<CompanyShowAllMemberGearsRequest, CompanyShowAllMemberGears> {

    private final CompanyMemberGearEntityRepository gearEntityRepository;

    @Override
    public CompanyShowAllMemberGears apply(CompanyShowAllMemberGearsRequest request) {

        if (Objects.nonNull(request.getCompanyName())) {

            final List<CompanyMemberGearEntity> gearList = gearEntityRepository.allCompanyMemberGears(request.getCompanyName());

            return CompanyShowAllMemberGears.builder().gearList(gearList).build();
        }

        return null;
    }
}
