package com.newworldcompanytracker.model.request_response;

import com.newworldcompanytracker.type.Rank;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class CompanyPermissionResponse {

    private String companyName;

    private String username;

    private String server;

    private Rank rank;
}
