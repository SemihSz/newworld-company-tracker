package com.newworldcompanytracker.service.register;

import com.newworldcompanytracker.entity.CompanyAdminEntity;
import com.newworldcompanytracker.entity.CompanyEntity;
import com.newworldcompanytracker.model.request_response.CompanyPermissionRequest;
import com.newworldcompanytracker.model.request_response.CompanyPermissionResponse;
import com.newworldcompanytracker.repository.CompanyAdminEntityRepository;
import com.newworldcompanytracker.service.task.SimpleTask;
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
public class PermissionRegisterService implements SimpleTask<CompanyPermissionRequest, CompanyPermissionResponse> {

    private final CompanyAdminEntityRepository companyAdminEntityRepository;

    private final IsCompanyAdminExistService isCompanyAdminExistService;

    private final IsCompanyExistService isCompanyExistService;

    @Override
    public CompanyPermissionResponse apply(CompanyPermissionRequest companyPermissionRequest) {

        final boolean isAdminExist = isCompanyAdminExistService.test(companyPermissionRequest.getUsername());
        final boolean isExistCompany = isCompanyExistService.test(companyPermissionRequest.getCompanyName());

        if (Boolean.FALSE.equals(isAdminExist) && Boolean.TRUE.equals(isExistCompany)) {

            final CompanyAdminEntity companyAdminEntity = CompanyAdminEntity.builder()
                    .server(companyPermissionRequest.getServer())
                    .userRank(companyPermissionRequest.getRank())
                    .username(companyPermissionRequest.getUsername())
                    .companyName(companyPermissionRequest.getCompanyName())
                    .build();

            companyAdminEntityRepository.save(companyAdminEntity);

            return CompanyPermissionResponse.builder()
                    .companyName(companyAdminEntity.getCompanyName())
                    .username(companyAdminEntity.getUsername())
                    .rank(companyAdminEntity.getUserRank())
                    .server(companyAdminEntity.getServer())
                    .build();
        }

        return null;
    }
}
