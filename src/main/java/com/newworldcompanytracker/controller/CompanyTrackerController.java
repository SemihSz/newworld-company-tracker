package com.newworldcompanytracker.controller;

import com.newworldcompanytracker.config.CompanySecurityAnnotation;
import com.newworldcompanytracker.model.ExcelResponse;
import com.newworldcompanytracker.model.RestResponse;
import com.newworldcompanytracker.model.request_response.*;
import com.newworldcompanytracker.service.GeneralCompanyTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CompanyTrackerController {

    private final GeneralCompanyTrackerService generalCompanyTrackerService;

    @PostMapping(value = "/register-company", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<CompanyAuthResponse>> registerAuth(CompanyAuthRequest request) {

        return ResponseEntity.ok(new RestResponse<>(200, generalCompanyTrackerService.registerCompany(request)));
    }

    @PostMapping(value = "/register-permission", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CompanySecurityAnnotation(authToken = "[0].authToken", companyName = "[0].companyName")
    ResponseEntity<RestResponse<CompanyPermissionResponse>> registerPermission(CompanyPermissionRequest request) {

        return ResponseEntity.ok(new RestResponse<>(200, generalCompanyTrackerService.registerPermission(request)));
    }

    @PostMapping(value = "/save-members-gearscore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<RestResponse<CompanyMembersGearResponse>> saveMembersGears(CompanyMemberRegisterGearRequest request) {

        return ResponseEntity.ok(new RestResponse<>(200, generalCompanyTrackerService.saveMemberGear(request)));
    }

    @PostMapping(value = "/remove-members-gearscore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CompanySecurityAnnotation(authToken = "[0].authToken", companyName = "[0].companyName")
    ResponseEntity<RestResponse<CompanyMembersGearResponse>> removeMembersGears(CompanyMemberRemoveGearRequest request) {

        return ResponseEntity.ok(new RestResponse<>(200, generalCompanyTrackerService.removeMemberGear(request)));
    }

    @PostMapping(value = "/shows-all-members-gearscore", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CompanySecurityAnnotation(authToken = "[0].authToken", companyName = "[0].companyName")
    ResponseEntity<RestResponse<CompanyShowAllMemberGears>> showsAllMemberGears(CompanyShowAllMemberGearsRequest request) {

        return ResponseEntity.ok(new RestResponse<>(200, generalCompanyTrackerService.showAllMemberGears(request)));
    }

    @PostMapping(value = "/excel-score", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CompanySecurityAnnotation(authToken = "[0].authToken", companyName = "[0].companyName")
    ResponseEntity<InputStreamResource> showsAllMemberGearsExcel(CompanyExcelRequest request) {

        final ExcelResponse response = generalCompanyTrackerService.getAllCompanyMembersExcel(request);
        final ByteArrayInputStream inputStream = response.getByteArrayInputStream();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=gear-score-list.xlsx");
        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(inputStream));
    }

//    @PostMapping("single/uploadDb")
//    ResponseEntity<RestResponse<Boolean>> singleFileUplaod(@RequestParam("file") MultipartFile file) throws IOException {
//
//        String name = StringUtils.cleanPath(file.getOriginalFilename());
//        FileDocument fileDocument = new FileDocument();
//        fileDocument.setFileName(name);
//        fileDocument.setDocFile(file.getBytes());
//
//        docFileDao.save(fileDocument);
//
//        ///http://localhost:8081/download/abc.jpg
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFromDB/")
//                .path(name)
//                .toUriString();
//
//        String contentType = file.getContentType();
//
//        FileUploadResponse response = new FileUploadResponse(name, contentType, url);
//
//        return response;
//
//    }


}
