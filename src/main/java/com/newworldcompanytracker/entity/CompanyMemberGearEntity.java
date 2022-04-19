package com.newworldcompanytracker.entity;

import com.newworldcompanytracker.type.Rank;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(CompanyMemberGearEntity.PK.class)
public class CompanyMemberGearEntity {

    @Id
    private String companyName;

    @Id
    private String username;

    @Id
    private Integer buildNumber;

    @EqualsAndHashCode
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        private String companyName;

        private String username;

        private Integer buildNumber;
    }

    private Integer gearScore;

    private String description;

    private String mainBuildName;

    private String stats;

    private Boolean isWarBuild;

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
