package com.newworldcompanytracker.model.request_response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class CompanyAuthResponse {

    private String companyName;

    private String status;

    private String leaderName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdDate;

    private String authToken;

    private String server;
}
