package com.newworldcompanytracker.model.input;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Builder
@Getter
public class CompanyRegisterInput {

    private String companyName;

    private String leaderName;

    private int memberSize;

    private String server;

}
