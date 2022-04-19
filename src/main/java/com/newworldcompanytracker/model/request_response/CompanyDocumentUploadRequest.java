package com.newworldcompanytracker.model.request_response;

import com.newworldcompanytracker.model.DocumentGallery;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */

@Getter
@Setter
public class CompanyDocumentUploadRequest {

    private String companyName;

    private String username;

    private List<DocumentGallery> gallery;

}
