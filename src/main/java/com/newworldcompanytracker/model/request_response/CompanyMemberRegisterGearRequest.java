package com.newworldcompanytracker.model.request_response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class CompanyMemberRegisterGearRequest {

    // General Info

    private String username;

    private String company;

    private Integer gearScore;

    private Integer buildNumber;

    private String description;

    private String mainBuildName;

    private Boolean isWarBuild;

    private String stats;

    // Head

    private String gearHeadPerks;

    // Chest

    private String gearChestPerks;

    // Gloves

    private String gearGlovesPerks;

    // Leg

    private String gearLegPerks;

    // Foot

    private String gearFootPerks;

    // Amulet

    private String gearAmuletPerks;

    // Ring

    private String gearRingPerks;

    // Earring

    private String gearEarringPerks;


    // Weapon First

    private String gearWeaponFirstPerks;

    // Weapon Second

    private String gearWeaponSecondPerks;

}
