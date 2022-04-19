package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import com.newworldcompanytracker.model.request_response.CompanyMemberRegisterGearRequest;
import com.newworldcompanytracker.model.request_response.CompanyMembersGearResponse;
import com.newworldcompanytracker.repository.CompanyMemberGearEntityRepository;
import com.newworldcompanytracker.service.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyMemberRegisterGearService implements SimpleTask<CompanyMemberRegisterGearRequest, CompanyMembersGearResponse> {

    private final CompanyMemberGearEntityRepository companyMemberGearEntityRepository;

    @Override
    public CompanyMembersGearResponse apply(CompanyMemberRegisterGearRequest request) {

        final CompanyMemberGearEntity memberGear = CompanyMemberGearEntity.builder()
                .companyName(request.getCompany())
                .username(request.getUsername())
                .buildNumber(request.getBuildNumber())
                .gearScore(request.getGearScore())
                .description(request.getDescription())
                .mainBuildName(request.getMainBuildName())
                .stats(request.getStats())
                .isWarBuild(request.getIsWarBuild())
                .gearHeadPerks(request.getGearHeadPerks())
                .gearChestPerks(request.getGearChestPerks())
                .gearGlovesPerks(request.getGearGlovesPerks())
                .gearLegPerks(request.getGearLegPerks())
                .gearFootPerks(request.getGearFootPerks())
                .gearAmuletPerks(request.getGearAmuletPerks())
                .gearRingPerks(request.getGearRingPerks())
                .gearEarringPerks(request.getGearEarringPerks())
                .gearWeaponFirstPerks(request.getGearWeaponFirstPerks())
                .gearWeaponSecondPerks(request.getGearWeaponSecondPerks())
                .build();

        companyMemberGearEntityRepository.save(memberGear);

        return CompanyMembersGearResponse.builder().status(true).build();
    }
}
