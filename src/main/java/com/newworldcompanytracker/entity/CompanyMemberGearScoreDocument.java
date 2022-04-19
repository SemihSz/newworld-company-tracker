package com.newworldcompanytracker.entity;

import com.newworldcompanytracker.type.DocumentType;
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
@IdClass(CompanyMemberGearScoreDocument.PK.class)
public class CompanyMemberGearScoreDocument {

    @Id
    private String companyName;

    @Id
    private String username;

    @EqualsAndHashCode
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PK implements Serializable {

        private String companyName;

        private String username;
    }

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Lob
    private byte[] docFile;
}
