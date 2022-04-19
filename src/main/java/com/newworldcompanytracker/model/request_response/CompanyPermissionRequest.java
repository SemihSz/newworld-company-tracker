package com.newworldcompanytracker.model.request_response;

import com.newworldcompanytracker.type.Rank;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class CompanyPermissionRequest {

    private String companyName;

    private String username;

    private Rank rank;

    private String server;
}
