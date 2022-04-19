package com.newworldcompanytracker;

import com.newworldcompanytracker.entity.CompanyMemberGearScoreDocument;
import com.newworldcompanytracker.model.ExcelResponse;
import com.newworldcompanytracker.model.input.CompanyRegisterInput;
import com.newworldcompanytracker.model.request_response.*;
import com.newworldcompanytracker.service.GenerateExcelFileService;
import com.newworldcompanytracker.service.ShowAllCompanyMembersGearScores;
import com.newworldcompanytracker.service.register.*;
import com.newworldcompanytracker.service.GeneralCompanyTrackerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyTrackerService implements GeneralCompanyTrackerService {

    private final IsCompanyExistService isCompanyExistService;

    private final CompanyRegisterService companyRegisterService;

    private final PermissionRegisterService permissionRegisterService;

    private final CompanyMemberRegisterGearService companyMemberRegisterGearService;

    private final CompanyMemberRemoveGearService companyMemberRemoveGearService;

    private final ShowAllCompanyMembersGearScores showAllCompanyMembersGearScores;

    private final GenerateExcelFileService generateExcelFileService;

    private final String [] excelCell = {
            "Company Name", "Username", "Build Number",
            "GearScore", "Description", "Main Build Name",
            "Stats", "Head Perks", "Chest Perks",
            "Gloves Perks", "Leg Perks", "Foot Perks",
            "Amulet Perks", "Ring Perks", "Earring Perks",
            "Weapon-1 Perks",  "Weapon-2 Perks", "Is War Build?"};


    @Override
    public CompanyAuthResponse registerCompany(CompanyAuthRequest request) {

        final boolean isExistCompany = isCompanyExistService.test(request.getCompanyName());

        if (Boolean.FALSE.equals(isExistCompany)) {

            final CompanyRegisterInput registerInput = CompanyRegisterInput.builder()
                    .companyName(request.getCompanyName())
                    .leaderName(request.getLeaderName())
                    .server(request.getServer())
                    .memberSize(request.getMemberSize())
                    .build();

            final CompanyRegisterResponse registerResponse = companyRegisterService.apply(registerInput);

            return CompanyAuthResponse.builder()
                    .companyName(registerResponse.getCompanyName())
                    .leaderName(registerResponse.getLeaderName())
                    .authToken(registerResponse.getAuthToken())
                    .createdDate(registerResponse.getCreatedDate())
                    .status("1")
                    .server(registerInput.getServer())
                    .build();
        }

        return null;

    }

    @Override
    public CompanyPermissionResponse registerPermission(CompanyPermissionRequest request) {

        final boolean isExistCompany = isCompanyExistService.test(request.getCompanyName());

        if (Boolean.TRUE.equals(isExistCompany)) {

            return permissionRegisterService.apply(request);
        }

        return null;
    }

    @Override
    public CompanyMembersGearResponse saveMemberGear(CompanyMemberRegisterGearRequest request) {
        return companyMemberRegisterGearService.apply(request);
    }

    @Override
    public CompanyMembersGearResponse removeMemberGear(CompanyMemberRemoveGearRequest request) {
        return companyMemberRemoveGearService.apply(request);
    }

    @Override
    public CompanyShowAllMemberGears showAllMemberGears(CompanyShowAllMemberGearsRequest request) {
        return showAllCompanyMembersGearScores.apply(request);
    }

    @Override
    public Boolean uploadFile(CompanyDocumentUploadRequest file) {

        return null;
    }

    @Override
    public ExcelResponse getAllCompanyMembersExcel(CompanyExcelRequest request) {
        CompanyShowAllMemberGearsRequest requestAll = new CompanyShowAllMemberGearsRequest();
        requestAll.setCompanyName(request.getCompanyName());

        final CompanyShowAllMemberGears showAllMemberGears = showAllMemberGears(requestAll);

        try {
            return generateExcelFileService.byteArray(showAllMemberGears.getGearList(), excelCell);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    //    @Override
//    public Boolean uploadFile(CompanyDocumentUploadRequest request) {
//
//        String name = StringUtils.cleanPath(file.());
//        final CompanyMemberGearScoreDocument fileDocument = CompanyMemberGearScoreDocument.builder()
//                .companyName()
//                .build()
//        fileDocument.setFileName(name);
//        fileDocument.setDocFile(file.getBytes());
//        return null;
//    }
}
