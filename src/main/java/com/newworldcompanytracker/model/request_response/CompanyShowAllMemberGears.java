package com.newworldcompanytracker.model.request_response;

import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Builder
public class CompanyShowAllMemberGears {

    private List<CompanyMemberGearEntity> gearList;
}
