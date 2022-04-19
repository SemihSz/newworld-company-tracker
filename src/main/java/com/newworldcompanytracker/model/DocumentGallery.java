package com.newworldcompanytracker.model;

import com.newworldcompanytracker.type.DocumentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Getter
@Setter
public class DocumentGallery {

    private MultipartFile file;

    private DocumentType documentType;

}
