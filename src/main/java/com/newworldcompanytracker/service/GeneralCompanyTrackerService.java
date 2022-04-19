package com.newworldcompanytracker.service;

import com.newworldcompanytracker.model.ExcelResponse;
import com.newworldcompanytracker.model.request_response.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public interface GeneralCompanyTrackerService {

    CompanyAuthResponse registerCompany(CompanyAuthRequest request);

    CompanyPermissionResponse registerPermission(CompanyPermissionRequest request);

    CompanyMembersGearResponse saveMemberGear(CompanyMemberRegisterGearRequest request);

    CompanyMembersGearResponse removeMemberGear(CompanyMemberRemoveGearRequest request);

    CompanyShowAllMemberGears showAllMemberGears(CompanyShowAllMemberGearsRequest request);

    ExcelResponse getAllCompanyMembersExcel(CompanyExcelRequest request);

    Boolean uploadFile(CompanyDocumentUploadRequest file);
}
