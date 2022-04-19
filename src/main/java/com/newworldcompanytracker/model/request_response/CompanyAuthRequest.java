package com.newworldcompanytracker.model.request_response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class CompanyAuthRequest {

    private String companyName;

    private String leaderName;

    private int memberSize;

    private String server;
}
