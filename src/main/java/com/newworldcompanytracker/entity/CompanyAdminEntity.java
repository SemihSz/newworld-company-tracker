package com.newworldcompanytracker.entity;

import com.newworldcompanytracker.type.Rank;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@IdClass(CompanyAdminEntity.PK.class)
public class CompanyAdminEntity {

    @Id
    private String companyName;

    @Id
    private String username;

    @Enumerated(EnumType.STRING)
    private Rank userRank;

    private String server;

    @EqualsAndHashCode
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        private String companyName;

        private String username;
    }

}
